package edu.wctc;

import java.util.ArrayList;
import java.util.MissingFormatArgumentException;

public class Cookbook {

    // Hold all the meals that are read in from the file
    private ArrayList<Meal> meals = new ArrayList<>(100);
    // Hold the next (empty) index in the array

    public void addElementWithStrings(String mealTypeStr, String mealNameStr, String caloriesStr) {
        MealType mealType;

        // Do we have room in the array for one more?


            // Find the correct enum using a switch? Or use .fromValue() instead?
            switch (mealTypeStr) {
                case "Breakfast":
                    mealType = MealType.BREAKFAST;
                    break;
                case "Lunch":
                    mealType = MealType.LUNCH;
                    break;
                case "Dinner":
                    mealType = MealType.DINNER;
                    break;
                case "Dessert":
                    mealType = MealType.DESSERT;
                    break;
                default:
                    mealType = MealType.DINNER;
                    System.out.println("Meal Creation Error: Invalid Meal Type " + mealTypeStr + ", defaulted to Dinner.");
            }

            int calories;

            if (caloriesStr.matches("-?\\d+(\\.\\d+)?")) {
                calories = Integer.parseInt(caloriesStr);
            } else {
                calories = 100;
                System.out.println("Meal Creation Error: Invalid Calories " + caloriesStr + ", defaulted to 100.");
            }
            meals.add(new Meal(mealType, mealNameStr, calories));
    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void printAllMeals() {
        for (Meal item : meals) {
            if (item != null) {
                System.out.println(item);
            }
        }
    }


    public void printMealsByType(MealType mealType) {
        for (Meal item : meals) {
            if (item != null && item.getMealType() == mealType) {
                System.out.println(item);
            }
        }
    }
    public void printSubTotals() {
        try {


            System.out.printf("%9s %9s %9s %9s %9s %9s \n", "Meal Type", "Total", "Mean", "Min", "Max", "Median");
            MealType oldMealType = null;
            int count = 0;
            int subtotal = 0;
            int min = 0;
            int max = 0;
            double mean = 0;
            int median = 0;
            int index = 0;
            for (int i = 0; i < meals.size(); i++) {
                if (meals.get(i) != null) {

                    if (meals.get(i).getMealType() != oldMealType && oldMealType != null) {
                        System.out.printf("%-9s %9s %9s %9s %9s %9s \n", oldMealType.getMeal(), subtotal, String.format("%.2f", mean), min, max, median);
                    }
                    oldMealType = meals.get(i).getMealType();
                    if(min > meals.get(i).getCalories()){
                        min = meals.get(i).getCalories();
                    } else {
                        min = meals.get(i).getCalories();
                    }
                    count++;
                    subtotal += meals.get(i).getCalories();
                    mean = (double)subtotal / count;

                    if(max < meals.get(i).getCalories()){
                        max = meals.get(i).getCalories();
                    }
                    index = count/2;
                    median = meals.get(index).getCalories();

                }
            }

            System.out.printf("%-9s %9s %9s %9s %9s %9s \n", oldMealType.getMeal(), subtotal, String.format("%.2f", mean), min, max, median);
        } catch (NullPointerException e){
            System.out.println("Something is null, aborted Control Break. Error: " + e);
        } catch(MissingFormatArgumentException e){
            System.out.println(e);
        }
    }


    public void printByNameSearch(String s) {
        // Maybe add a message if no match found?
        for (Meal item : meals) {
            // Maybe make this case-insensitive?
            if (item != null && item.getItem().contains(s)) {
                System.out.println(item);
            }
        }
    }
}
