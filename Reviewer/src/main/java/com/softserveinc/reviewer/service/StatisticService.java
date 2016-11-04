package com.softserveinc.reviewer.service;

import com.google.inject.Inject;
import com.softserveinc.reviewer.dao.StatisticDao;
import com.softserveinc.reviewer.model.Statistic;

public class StatisticService {
    private final StatisticDao statisticDao;

    @Inject
    public StatisticService(StatisticDao statisticDao) {
        this.statisticDao = statisticDao;
    }

    public void saveStats(long duration, String methodName) {
        Statistic statistic = statisticDao.getOneByMethodName(methodName);

        if (statistic != null) {
            int invocationCount = statistic.getInvocationCount();
            long oldAverage = statistic.getAverage();
            long newAverage = (invocationCount * oldAverage + duration) / ++invocationCount;
            statistic.setAverage(newAverage);
            statistic.setInvocationCount(invocationCount);
            statisticDao.update(statistic);
        } else {
            statistic = new Statistic();
            statistic.setMethodName(methodName);
            statistic.setInvocationCount(1);
            statistic.setAverage(duration);
            statisticDao.create(statistic);
        }
    }

}
