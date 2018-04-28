package com.icloud.web.business.front.goods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icloud.common.ConfigUtil;
import com.icloud.model.business.BeanGoods;
import com.icloud.model.business.BeanGoodsClass;
import com.icloud.model.business.BeanGoodsWithBLOBs;
import com.icloud.service.business.BeanAdvertService;
import com.icloud.service.business.BeanGoodsClassService;
import com.icloud.service.business.BeanGoodsService;
import com.icloud.vo.GoodsVo;
import com.icloud.web.AppBaseController;

/**
 * 类名称: BeanGoodFrontController
 * 类描述: 手机端 商品首页 与商品详细页控制
 * 创建人: zhangdehai
 * 创建时间:2018年3月6日 上午11:45:24
 */
@Controller
@RequestMapping(value = "${frontPath}/beanGoods")
public class BeanGoodFrontController extends AppBaseController{
	
	@Autowired
	private BeanGoodsService beanGoodsService;
	@Autowired
	private BeanAdvertService beanAdvertService;
	@Autowired
	private BeanGoodsClassService beanGoodsClassService;
	
	/**
	* Description:商品列表页
	* @author zhangdehai
	* @param id
	* @return
	* @throws Exception  
	* @date 2018年2月27日下午4:43:42
	 */
	@RequestMapping(value = "/goodsList")
	public String goodsList(Integer id) throws Exception{
		BeanGoodsClass gooClass = new BeanGoodsClass();
		gooClass.setStatus("1");
		request.setAttribute("goodClassList",beanGoodsClassService.findList(gooClass));
		return "front/index";
	}
	
	/**
	* Description:异步根据商品分类获取商品列表页
	* @author zhangdehai
	* @param goodsclassId 商品分类获id
	* @return
	* @throws Exception  
	* @date 2018年2月27日下午4:43:42
	 */
	@RequestMapping(value = "/goodsListJson")
	@ResponseBody
	public String goodsListJson(Integer goodsclassId) throws Exception{
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			BeanGoods t = new BeanGoods();
			t.setStatus("1");//上架的
			t.setDeleteStatus("0");//没有被删除的
			t.setCategoriesId(goodsclassId);
			List<BeanGoods> beanGoodsList = beanGoodsService.findList(t);
			if(beanGoodsList!=null && beanGoodsList.size()>0){
				List<GoodsVo> newList = new ArrayList<GoodsVo>();
				for (BeanGoods beanGoods : beanGoodsList) {
					GoodsVo vo = new GoodsVo();
					vo.setGoodImage(ConfigUtil.get("pictureVisitUrl")+beanGoods.getGoodImage());
					vo.setId(beanGoods.getId());
					vo.setName(beanGoods.getName());
					vo.setScore(beanGoods.getScore());
					newList.add(vo);
				}
				resultMap.put(STATUS, SUCCESS);
				resultMap.put("beanGoodsList",newList);
				return pakageJsonp(resultMap);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		resultMap.put(STATUS, ERROR);
		return pakageJsonp(resultMap);
	}
	
	
	/**
	* Description:商品详情页
	* @author zhangdehai
	* @param id
	* @return  
	 * @throws Exception 
	* @date 2018年2月27日下午4:19:34
	 */
	@RequestMapping(value = "/goodsDetail")
	public String goodsDetail(Integer id) throws Exception{
		BeanGoodsWithBLOBs beanGoods = beanGoodsService.findByKey(id);
		request.setAttribute("beanGoods", beanGoods);
		return "front/goodsDetail";
	}

	/**
	* Description:库存校验
	* @author zhangdehai
	* @param goodsId
	* @param exChangeNum
	* @throws Exception  
	* @date 2018年2月28日上午10:19:26
	 */
	@RequestMapping(value = "/checkGoodsStore")
	@ResponseBody
	public String checkGoodsStore(Integer goodsId,Integer exChangeNum) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		BeanGoods beanGoods = beanGoodsService.findByKey(goodsId);
		if(beanGoods!=null && beanGoods.getStore()-beanGoods.getFreezeStore()>=exChangeNum){
			map.put(STATUS, SUCCESS);
		}
		map.put(STATUS, ERROR);
		map.put(MESSAGE, "库存不足");
		return pakageJsonp(map);
	}
	
	/**
	* Description:乐豆商城规则页面
	* @author zhangdehai
	* @return  
	* @date 2018年3月8日上午10:51:02
	 */
	@RequestMapping(value = "/rules")
	public String rules(){
		
		return "front/rules";
	}
}
