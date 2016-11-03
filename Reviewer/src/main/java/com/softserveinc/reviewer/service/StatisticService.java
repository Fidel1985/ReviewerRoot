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

    public void saveStats(double duration, String methodName) {
        Statistic statistic = statisticDao.getOneByMethodName(methodName);

        if (statistic != null) {
            int invocationCount = statistic.getInvocationCount();
            double oldAverage = statistic.getAverage();
            double newAverage = (invocationCount * oldAverage + duration) / ++invocationCount;
            statistic.setAverage(Math.round(newAverage * 10.0) / 10.0);
            statistic.setInvocationCount(invocationCount);
            statisticDao.update(statistic);
        } else {
            statistic = new Statistic();
            statistic.setMethodName(methodName);
            statistic.setInvocationCount(1);
            statistic.setAverage(Math.round(duration * 10.0) / 10.0);
            statisticDao.create(statistic);
        }
    }

}
