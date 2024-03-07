package oop.homeWork;

import com.sun.nio.sctp.SctpChannel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class PersonalInformation {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите данные (не разделяя запятой) Фамилия Имя Отчество дата рождения номер телефона пол:");
            String input = scanner.nextLine();

            String[] data = new String[]{input.replaceAll(",", "")};
            data = input.split(" ");
            if (data.length != 6) {
                System.out.println("Введено неверное количество данных. Пожалуйста, введите 6 значений.");
                return;
            }

            String lastName = data[0];
            String firstName = data[1];
            String middleName = data[2];
            String dateBirth = data[3];
            try {
                isDateValid(dateBirth);
            } catch (DateTimeParseException e) {
                System.out.println("Неверный формат даты рождения. Используйте формат yyyy-mm-dd");
                return;
            }
            long phoneNumber;
            try {
                phoneNumber = Long.parseLong(data[4]);
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат номера телефона. Используйте только цифры.");
                return;
            }
            char gender = data[5].charAt(0);
            if (gender != 'f' && gender != 'm') {
                System.out.println("Пол должен быть указан как f (женский) или m (мужской).");
                return;
            }

            String fileName = lastName + ".txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {

                String output = lastName + " " + firstName + " " + middleName + " " +
                        data[3] + " " +
                        phoneNumber + " " + gender;
                writer.write(output);
                writer.newLine();
                System.out.println("Данные успешно записаны в файл " + fileName);
            } catch (IOException e) {
                System.err.println("Ошибка при записи в файл: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.err.println("Произошла ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static boolean isDateValid(String date) {

        SimpleDateFormat myFormat = new SimpleDateFormat("dd.MM.yyyy");
        myFormat.setLenient(false);
        try {
            myFormat.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}


