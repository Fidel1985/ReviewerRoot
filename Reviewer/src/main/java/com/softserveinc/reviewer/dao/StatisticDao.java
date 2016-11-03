package com.softserveinc.reviewer.dao;

import com.softserveinc.reviewer.model.Statistic;

public interface StatisticDao {
    Statistic getOneByMethodName(String methodName);
    Statistic create(Statistic statistic);
    Statistic update(Statistic statistic);
}
