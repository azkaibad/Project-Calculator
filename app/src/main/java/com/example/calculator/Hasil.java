package com.example.calculator;

public class Hasil{

    private final String aNomor1;
    private final String aOperasi;
    private final String aNomor2;
    private final String aHasil;

    public Hasil(String nomor1, String operasi, String nomor2, String hasil){
        aNomor1 = nomor1;
        aOperasi = operasi;
        aNomor2 = nomor2;
        aHasil = hasil;
    }

    public String getNomor1(){return aNomor1;}

    public String getOperasi(){return aOperasi;}

    public String getNomor2(){return aNomor2;}

    public String getHasil(){return aHasil;}
}