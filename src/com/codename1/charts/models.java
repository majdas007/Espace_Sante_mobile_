/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.charts;

/**
 *
 * @author majd
 */
public class models {
  

    public static class CategorySeries {


        public CategorySeries(String title) {
            
        }

        public void add(String string, double value) {
            
        }
          protected CategorySeries buildCategoryDataset(String title, double[] values) {
        CategorySeries series = new CategorySeries(title);
        int k = 0;
        for (double value : values) {
            series.add("Project " + ++k, value);
        }

        return series;
    }
       
        
     
    }
    
}
