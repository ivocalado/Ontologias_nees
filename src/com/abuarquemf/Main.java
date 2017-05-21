package com.abuarquemf;

import com.abuarquemf.persistence.DataBaseHandler;
import com.google.gson.Gson;

/**
 * Created by animal505 on 20/05/17.
 */
public class Main {
    public static void main(String[] args) {
        DataBaseHandler dataBaseHandler = DataBaseHandler.getInstance();

        //imprimindo a lista toda
        System.out.println(dataBaseHandler.getAvatars());
        System.out.println("\n\n");

        //imprimindo um avatar especifico pelo nome
        System.out.println(dataBaseHandler.findAvatar("adriana_marangoni"));

        System.out.println("\n\n");

        //pegando toda a lista e transformando em json
        Gson g = new Gson();
        System.out.println(
                g.toJson(dataBaseHandler.getAvatars()).toLowerCase());
    }
}
