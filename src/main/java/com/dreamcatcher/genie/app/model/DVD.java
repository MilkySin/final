package com.dreamcatcher.genie.app.model;

public class DVD extends items{
    private String genre;

    public DVD(String ID, String title, String type, int loan, int numOfCopies, double price, Boolean rentalStatus, String genre) {
        super(ID, title, type, loan, numOfCopies, price, rentalStatus);
        this.genre = genre;
    }
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        if (genre.equalsIgnoreCase("Action")||genre.equalsIgnoreCase("Horror")||genre.equalsIgnoreCase("Drama")||genre.equalsIgnoreCase("Comedy"))
            this.genre = genre;
        else
            System.out.println("invalid genre");
    }
}
