package com.icloud.service.master;
import java.util.Map;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.icloud.common.util.wx.HttpRequestUtil;
import com.icloud.exceptions.BeanException;
import com.icloud.service.business.ExChangeService;
import com.icloud.vo.ExchangeGoodsVo;
/**
 * @filename      : Worker.java
 * @description   : Worker进程实现
 * @author        : zdh
 * @create        : 2017年7月25日 下午4:52:32   
 * @copyright     : zhumeng.com@multitask
 *
 * Modification History:
 * Date             Author       Version
 * --------------------------------------
 */
public class Worker  implements Runnable{

	private final static Logger log = LoggerFactory.getLogger(Worker.class);
	//任务队列，用于取得子任务
	protected Queue<String> workQueue;
	//子任务处理结果集
	protected Map<String ,Object> resultMap;
	
	public void setWorkQueue(Queue<String> workQueue){
		this.workQueue= workQueue;
	}
	
	public void setResultMap(Map<String ,Object> resultMap){
		this.resultMap=resultMap;
	}

	//子任务处理的逻辑，在子类中实现具体逻辑
	public boolean handle(String url){
		try{
			HttpRequestUtil.httpPost(url);
		}catch(BeanException e){
			e.printStackTrace();
			log.error(e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return true;
	}
	
	@Override
	public void run() {
			//获取子任务
			while(true){
				String input= workQueue.poll();
				log.error(input!=null?input:"==================");
				if(input==null){
					break;
				}
				//处理子任务
				boolean re = handle(input);
				resultMap.put(Integer.toString(input.hashCode()), re);
			}
	}

}
