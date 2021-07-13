package com.zensio;

import lombok.Data;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

@Data
public class InvoiceGenerator {
    private static final int OPEN_SEAT_PRICE = 5000;
    private static final int CABIN_SEAT_PRICE = 10000;
    private static final int CONFERENCE_ROOM_PRICE = 200;
    private static final int MEAL_PRICE = 100;
    private static final int FREE_CONFERENCE_ROOM_HOURS_WITH_OPEN_SEATS = 5;
    private static final int FREE_CONFERENCE_ROOM_HOURS_WITH_CABIN_SEATS = 10;

    private CoworkingFacility openSeats;
    private CoworkingFacility cabinSeats;
    private CoworkingFacility conferenceRoomUsageHours;
    private CoworkingFacility meals;

    public InvoiceGenerator(int openSeats, int cabinSeats, int conferenceRoomUsageHours, int meals) {
        this.openSeats = new CoworkingFacility(openSeats, OPEN_SEAT_PRICE, 18);
        this.cabinSeats = new CoworkingFacility(cabinSeats, CABIN_SEAT_PRICE, 18);
        if (conferenceRoomUsageHours >
                (openSeats * FREE_CONFERENCE_ROOM_HOURS_WITH_OPEN_SEATS + cabinSeats * FREE_CONFERENCE_ROOM_HOURS_WITH_CABIN_SEATS)) {
            this.conferenceRoomUsageHours = new CoworkingFacility(conferenceRoomUsageHours -
                    (openSeats * FREE_CONFERENCE_ROOM_HOURS_WITH_OPEN_SEATS + cabinSeats * FREE_CONFERENCE_ROOM_HOURS_WITH_CABIN_SEATS),
                    CONFERENCE_ROOM_PRICE, 18);
        } else this.conferenceRoomUsageHours = new CoworkingFacility(0, CONFERENCE_ROOM_PRICE, 18);
        this.meals = new CoworkingFacility(meals, MEAL_PRICE, 12);
    }

    String printInvoice() {
        String openSeats = priceString(this.openSeats.getTotalPrice(), this.openSeats.getQuantity() +
                " Open Seats : " + this.openSeats.getTotalPrice() + "\n");
        String cabinSeats = priceString(this.cabinSeats.getTotalPrice(), this.cabinSeats.getQuantity() +
                " Cabin Seats : " + this.cabinSeats.getTotalPrice() + "\n");
        String conferenceRooms = priceString(this.conferenceRoomUsageHours.getTotalPrice(),
                this.conferenceRoomUsageHours.getQuantity() +
                        " Hours of conference room usage : " + this.conferenceRoomUsageHours.getTotalPrice() + "\n");
        String meals = priceString(this.meals.getTotalPrice(), this.meals.getQuantity() +
                " Meals : " + this.meals.getTotalPrice() + "\n");
        return openSeats + cabinSeats + conferenceRooms + meals +
                "Total : " + getTotalPrice() + "\n" + "Total GST : " + getTotalGST() + "\n";
    }

    private String priceString(int price, String printString) {
        if (price > 0) {
            return printString;
        }
        return "";
    }

    private int getTotalPrice() {
        return getTotal(openSeats.getTotalPrice(), cabinSeats.getTotalPrice(), conferenceRoomUsageHours.getTotalPrice(),
                meals.getTotalPrice());
    }

    private int getTotalGST() {
        return getTotal(openSeats.getTotalGST(), cabinSeats.getTotalGST(), conferenceRoomUsageHours.getTotalGST(),
                meals.getTotalGST());
    }

    private int getTotal(int openSeatsPrice, int cabinSeatsPrice, int conferenceRoomUsagePrice, int mealsPrice) {
        return openSeatsPrice + cabinSeatsPrice + conferenceRoomUsagePrice + mealsPrice;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(in);
        out.println("Enter number of open seats : ");
        var openSeats = sc.nextInt();
        out.println("Enter number of cabin seats : ");
        var cabinSeats = sc.nextInt();
        out.println("Enter number of hours of conference room usage : ");
        var conferenceRoomHours = sc.nextInt();
        out.println("Enter number of meals : ");
        var meals = sc.nextInt();
        sc.close();
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator(openSeats, cabinSeats, conferenceRoomHours, meals);
        out.println(invoiceGenerator.printInvoice());
    }
}

