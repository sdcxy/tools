package com.github.sdcxy.service;

import java.util.List;

public interface DataDictionary<T> {

    List<T> getTableInfo(String tableNames);


}
