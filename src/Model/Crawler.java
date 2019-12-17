package Model;

abstract class Crawler {

    String inputParser(String input){
        String[] splited = input.split(" ");
        if (splited.length != 1) {
            String result = "";
            for (int i = 0; i < splited.length - 1; i++) {
                result += splited[i];
                result += "+";
            }
            result += splited[splited.length-1];
            return result;
        } else {
            return input;
        }
    }
}
