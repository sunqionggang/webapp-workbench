package com.seaboxdata.cqny.origin.test;

import java.util.List;

/**
 * 树形数据实体接口 
 * @param <T>
 */
public interface ITree<T> {
	
     String getId();
	
     String getParentId();
    
     void setChildList(List<T> childList);
    
     List<T> getChildList();
}