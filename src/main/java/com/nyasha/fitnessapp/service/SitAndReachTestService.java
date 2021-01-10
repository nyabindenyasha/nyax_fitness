package com.nyasha.fitnessapp.service;

import com.nyasha.fitnessapp.models.SitAndReachTest;
import com.nyasha.fitnessapp.models.SitAndReachTestCreateContext;

import java.util.Collection;

public interface SitAndReachTestService extends BaseService<SitAndReachTest, SitAndReachTestCreateContext, SitAndReachTestCreateContext> {

    Collection<SitAndReachTest> findByLoggedInUser(long userId);

}
