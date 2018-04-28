package com.icloud.service.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icloud.dao.business.BeanGoodsMapper;
import com.icloud.exceptions.BeanException;
import com.icloud.model.business.BeanGoods;
import com.icloud.model.business.BeanGoodsWithBLOBs;
import com.icloud.web.util.RedisBeanUtil;

@Service
public class BeanGoodsService{
	
	@Autowired
	private BeanGoodsMapper beanGoodsMapper;
	@Autowired
	private RedisBeanUtil<BeanGoods> redisBeanUtil;
	
	@CacheEvict(value = "goodsList")
	public void save(BeanGoodsWithBLOBs t) throws Exception {
		// TODO Auto-generated method stub
		beanGoodsMapper.insert(t);
		redisBeanUtil.setBeanGoods(t);
	}
	@CacheEvict(value = "goodsList")
	public void update(BeanGoodsWithBLOBs t) throws Exception {
		beanGoodsMapper.updateByPrimaryKeySelective(t);
		redisBeanUtil.setBeanGoods(beanGoodsMapper.selectByPrimaryKey(t.getId()));
	}
	@Cacheable(value = "goodsList")
	public List<BeanGoods> findList(BeanGoods t)
			throws Exception {
		// TODO Auto-generated method stub
		return beanGoodsMapper.findForList(t);
	}

	public Integer findCount(BeanGoodsWithBLOBs t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@CacheEvict(value = "goodsList")
	public void delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		beanGoodsMapper.deleteByPrimaryKey(id);
	}

	public BeanGoodsWithBLOBs findByKey(Integer id) {
		// TODO Auto-generated method stub
		BeanGoodsWithBLOBs beanGoods = beanGoodsMapper.selectByPrimaryKey(id);
		BeanGoods obj = redisBeanUtil.getBeanGoods(id);
		if(obj==null){
			redisBeanUtil.setBeanGoods(beanGoods);
		}
		return beanGoods;
	}

	public PageInfo<BeanGoods> findByPage(int pageNo, int pageSize,
			BeanGoods t) throws Exception {
		 PageHelper.startPage(pageNo, pageSize);
		 PageInfo<BeanGoods> page = new PageInfo<BeanGoods>(beanGoodsMapper.findForList(t));
		 return page;
	}
	@CacheEvict(value = "goodsList")
	@Transactional
	public int updateGoodsStore(BeanGoods beanGoods,Integer exChangeNum){
		BeanGoods obj = redisBeanUtil.getBeanGoods(beanGoods.getId());
		if(beanGoods.getStore()-obj.getFreezeStore()<exChangeNum){
			throw new BeanException("商品库存不足");
		}
		beanGoods.setFreezeStore(obj.getFreezeStore()!=null?obj.getFreezeStore()+exChangeNum:exChangeNum);
		beanGoods.setRealSales(obj.getRealSales()!=null?obj.getRealSales()+exChangeNum:exChangeNum);
		beanGoods.setVirtlSales(obj.getVirtlSales()!=null?obj.getVirtlSales()+exChangeNum:exChangeNum);
		int reslut = beanGoodsMapper.updateGoodsStore(beanGoods);
		//库存更新成功,刷新redis缓存
		if(reslut==1){
//			obj.setFreezeStore(obj.getFreezeStore()+exChangeNum);
//			obj.setRealSales(obj.getRealSales()+exChangeNum);
//			obj.setVirtlSales(obj.getVirtlSales()+exChangeNum);
			redisBeanUtil.setBeanGoods(beanGoods.getId(),exChangeNum);
		}
		return reslut;
	}
}
