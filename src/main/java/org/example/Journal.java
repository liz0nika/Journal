package org.example;

import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Journal {
    private final String studentSurname;
    private final String studentName;
    private final String dateOfBirth;
    private final String phoneNumber;
    private final String address;

    public Journal(String studentSurname, String studentName, String dateOfBirth, String phoneNumber, String address) {
        this.studentSurname = studentSurname;
        this.studentName = studentName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public boolean isLetters(String name) {
        char[] chars = name.toCharArray();
        for (char c : chars) {
            if (!Character.isLetter(c) && c != '-' && c != '`') {
                return false;
            }
        }
        return true;
    }

    public boolean isValidDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        try {
            Date parsedDate = dateFormat.parse(date);
            Date currentDate = new Date();

            return parsedDate.before(currentDate);
        }
        catch (ParseException e) {
            System.out.println("Invalid date format. Use dd-MM-yyyy.");
            return false;
        }
    }

    public boolean isDigits(String phoneNumber) {
        String pattern = "^\\+380[0-9]{9}$";
        return phoneNumber.matches(pattern);
    }

    public boolean isValidAddress(String address) {
        String[] parts = address.split(", ");
        if (parts.length != 2) {
            return false;
        }

        String street = parts[0].trim();
        String houseWithApartment = parts[1].trim();

        return isValidStreet(street) && isValidHouseWithApartment(houseWithApartment);
    }

    private boolean isValidStreet(String street) {
        String streetPattern = "^[А-ЩЬЮЯҐЄІЇа-щьюяґєії\\s'-]+$";
        return street.matches(streetPattern);
    }

    private boolean isValidHouseWithApartment(String houseWithApartment) {
        String pattern = "^\\d+\\s*\\w*$";
        return houseWithApartment.matches(pattern);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        if (isLetters(studentSurname)) {
            result.append("\nПрізвище: ").append(studentSurname);
        } else {
            result.append("\nПрізвище: invalid");
        }

        if (isLetters(studentName)) {
            result.append("\nІм'я: ").append(studentName);
        } else {
            result.append("\nІм'я: invalid");
        }

        if (isValidDate(dateOfBirth)) {
            result.append("\nДата народження: ").append(dateOfBirth);
        } else {
            result.append("\nДата народження: invalid");
        }

        if (isDigits(phoneNumber)) {
            result.append("\nНомер телефону: ").append(phoneNumber);
        } else {
            result.append("\nНомер телефону: invalid");
        }

        if (isValidAddress(address)) {
            result.append("\nДомашня адреса: ").append(address);
        } else {
            result.append("\nДомашня адреса: invalid");
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть прізвище студента:");
        String surname = scanner.nextLine();

        System.out.println("Введіть ім'я студента:");
        String name = scanner.nextLine();

        System.out.println("Введіть дату народження студента (у форматі ДД-ММ-РРРР, наприклад 01-01-2001):");
        String dateOfBirth = scanner.nextLine();

        System.out.println("Введіть номер телефону студента (у форматі +380999999999):");
        String phoneNumber = scanner.nextLine();

        System.out.println("Введіть домашню адресу студента (у форматі 'Вулиця, Номер будинку [квартира]', наприклад 'Печерська, 17'):");
        String address = scanner.nextLine();

        Journal journal = new Journal(surname, name, dateOfBirth, phoneNumber, address);
        System.out.println(journal);
    }
}

