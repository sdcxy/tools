package com.github.sdcxy.service.sql;

import java.util.List;

public interface DataDictionary<T> {

    List<T> getTableInfo(String tableNames);


}
