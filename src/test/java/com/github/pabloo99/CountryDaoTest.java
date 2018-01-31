package com.github.pabloo99;

import com.github.pabloo99.dao.CountryDao;
import com.github.pabloo99.entity.Country;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class CountryDaoTest {

    @Test
    public void shouldFindAllCountries(){
        CountryDao countryDao = new CountryDao();

        List<Country> countryList = countryDao.findAll();

        Assert.assertTrue(countryList.size() > 0);
    }

    @Test
    public void shouldFindCountryById(){
        CountryDao countryDao = new CountryDao();

        Country country = countryDao.findById("AR");

        assertEquals(country.getName(), "Argentina");
    }
}