package model;


public class EncryptDecrypt {

    public static String encrypt(String s){
        char[] st = s.toCharArray();
        int i = 0;
        for(char c: st)
        {
            if (c >= 97 && c <= 122){

                if (c + 2 < 122) {
                    st[i] = (char) (c + 2);
                }
                else{
                    st[i] = (char) (c + 2 - 26);
                }
            }
            else if(c >= 65 && c <= 91){

                if (c + 2 < 91) {
                    st[i] = (char) (c + 2);
                }
                else{
                    st[i] = (char) (c + 2 - 26);
                }
            }
            i++;
        }
        return new String(st);
    }




    public static String decrypt(String s){
        char[] st = s.toCharArray();
        int i = 0;
        for (char c: st){
            if (c >= 97 && c <= 122){
                if (c - 2 >= 97){
                    st[i] = (char) (c - 2);
                }
                else{
                    st[i] = (char) (c - 2 + 26);
                }
            }
            else if(c >= 65 && c <= 91){

                if (c - 2 >= 65) {
                    st[i] = (char) (c - 2);
                }
                else{
                    st[i] = (char) (c - 2 + 26);
                }
            }
            i++;
        }
        return new String(st);
    }


}
