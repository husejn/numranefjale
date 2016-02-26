/*
 * 
 */
package numranefjale;

/**
 *
 * @author Husejn
 */
public class NumraFjale {
    private final static String[] zeroTo10 = {"një", "dy", "tre", "katër", "pesë", "gjashtë", "shtatë", "tetë", "nentë", "dhjetë"};
    private final static String[] data = {" e ", "mbë", "dhjetë", "njëzet", "tridhjetë", "qind", "zero"};
    private final static String[] big = {" mijë", " milion", " miliard", " trilion", " kadrilion", " quintilion"};
    private static String[] elevenTo19; 
    private static String[] twentyTo90;
    private static String[] hundredTo900;
    
    private String numbersFromString(String v){
        return v.replaceAll("[^0-9]","");
    }
    private long stringToLong(String v){
        return Long.parseLong(numbersFromString(v), 10);
    }
    private void generateNumberArrays(){
        for(int i = 11; i <= 19; ++i)
            elevenTo19[i-11] = zeroTo10[i-11] + data[1] + data[2];
        twentyTo90[0] = data[3]; // exeption in albanian
        twentyTo90[1] = data[4]; // exeption in albanian
        for(int i = 0; i <= 5; ++i)
            twentyTo90[i+2] = zeroTo10[i+3] + data[2];
        for(int i = 0; i <= 8; ++i)
            hundredTo900[i] = zeroTo10[i] + data[5];
    }
    private void setup(){
        elevenTo19 = new String[9];
        twentyTo90 = new String[8];
        hundredTo900 = new String[9];
        generateNumberArrays();
    }
    NumraFjale(){
        setup();
    }
    private int getLongLength(long l){
        int c = 0;
        long divider = 1000;
        while (l / divider > 0){
            divider *= 1000;
            c++;
        }
        return c;
    }
    private String zeroTo99(long n){
        if (n <= 10)
            return zeroTo10[(int)n - 1];
        if (n <= 19)
            return elevenTo19[(int)n - 11];
        return twentyTo90[(int)n / 10 - 2] + (n % 10 != 0 ? (data[0] + zeroTo10[(int)n % 10 - 1]) : "");
    }
    private String gen3(long n, int i){
        if( i == 0 ) return zeroTo999(n);
        return zeroTo999(n) + big[i-1];
    }
    private String zeroTo999(long n){
        if(n == 0)
            return data[6];
        if((int)n / 100 != 0)
            return hundredTo900[(int)n / 100 - 1] + ((n%100>0) ? data[0] + zeroTo99(n%100) : "");
        else 
            return zeroTo99(n);
    }
    private String generateText(long n){
        int stringLength = getLongLength(n);
        if(stringLength == 0) return zeroTo999(n);
        String text = "";
        for(int i = stringLength; i >= 0; i--){
            if(n / (long)Math.pow(1000,i)%1000 != 0)
                text = text + (text.length()==0?"":data[0]) + gen3((n / (long)Math.pow(1000,i)%1000), i);
        }
        return text;
    }
    /**
    * Returns the long converted to words. 
    * 
    * @param  s  Number as a String. All the characters except digits will be ignored
    * @return Number into Words
    */
    public String showWord(String s){
        long n = stringToLong(s);
        if(n < 0) return "minus " + generateText(-n);
        return generateText(n);
    }
    /**
    * Returns the long converted to words. 
    * 
    * @param  s  Number as a long.
    * @return Number into Words
    */
    public String showWord(long s){
        if(s < 0) return "minus " + generateText(-s);
        return generateText(s);
    }
    public String showWord(long s, long t){
        if(s < 0) return "minus " + showWord(-s, t);
        return generateText(s) + " pikë (.) " + generateText(t);
    }
}
