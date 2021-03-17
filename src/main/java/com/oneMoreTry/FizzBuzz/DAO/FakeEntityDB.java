//package com.oneMoreTry.FizzBuzz.DAO;
//
//import org.springframework.stereotype.Repository;
//
//@Repository("FakeDB")
//public class FakeEntityDB implements EntityDAO{
//    @Override
//    public String fetchAndRelease(String number, int fizzDivisor, int buzzDivisor) {
//        if (Integer.parseInt(number)%fizzDivisor == 0 && Integer.parseInt(number)%buzzDivisor == 0)
//            return "FIZZBUZZ";
//        else if(Integer.parseInt(number)%fizzDivisor == 0)
//            return "Fizz";
//        else if(Integer.parseInt(number)%buzzDivisor == 0)
//            return "Buzz";
//        return Integer.toString(Integer.parseInt(number));
//    }
//}
