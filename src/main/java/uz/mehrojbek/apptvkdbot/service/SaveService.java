package uz.mehrojbek.apptvkdbot.service;

import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import uz.mehrojbek.apptvkdbot.entity.Patient;
import uz.mehrojbek.apptvkdbot.entity.Price;
import uz.mehrojbek.apptvkdbot.repository.PatientRepository;
import uz.mehrojbek.apptvkdbot.repository.PriceRepository;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class SaveService {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    PriceRepository priceRepository;


    @SneakyThrows
    public SendMessage savePatient(Message message, String name) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(message.getChatId()));

        try {

            List<Patient> patientList = new ArrayList<>();

            //obtaining input bytes from a file
            FileInputStream fis = new FileInputStream("downloads/" + name);
//creating workbook instance that refers to .xls file
            XSSFWorkbook wb = new XSSFWorkbook(fis);
//creating a Sheet object to retrieve the object
            XSSFSheet sheet = wb.getSheetAt(0);
//evaluating cell type
            FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();

            String bolim = "";
            String palata = "";
            String qavat = "";
            for (Row row : sheet)     //iteration over row using for each loop
            {
                int emptyCells = 0;
                Patient patient = new Patient();
                if (row.getRowNum() > 0) {
                    for (Cell cell : row) {
                        int columnIndex = cell.getColumnIndex();

                        CellType cellType = cell.getCellType();
                        if ((cellType.equals(CellType._NONE) || cellType.equals(CellType.BLANK))
                                && columnIndex != 0 && columnIndex != 1 && columnIndex != 6) {
                            emptyCells++;
                            continue;
                        }

                        switch (columnIndex) {
                            case 0:
                                if (!cellType.equals(CellType.BLANK))
                                    bolim = cell.getStringCellValue();

                                if (bolim.contains("intensiv palata")) {
                                    patient.setSectionNumber(0);
                                }

                                if (bolim.contains("1-bo'lim")) {
                                    patient.setSectionNumber(1);
                                }

                                if (bolim.contains("2-bo'lim")) {
                                    patient.setSectionNumber(2);
                                }
                                break;
                            case 1:
                                if (!cellType.equals(CellType.BLANK)) {
                                    palata = cell.getStringCellValue();
                                }

                                if (!palata.contains("intensiv palata") && !palata.contains("intensiv")) {
                                    if (!palata.contains("16-palata") && !palata.contains("16")) {
                                        if (!palata.contains("15-palata") && !palata.contains("15")) {
                                            if (!palata.contains("14-palata") && !palata.contains("14")) {
                                                if (!palata.contains("13-palata") && !palata.contains("13")) {
                                                    if (!palata.contains("12-palata") && !palata.contains("12")) {
                                                        if (!palata.contains("11-palata") && !palata.contains("11")) {
                                                            if (!palata.contains("10-palata") && !palata.contains("10")) {
                                                                if (!palata.contains("1-palata") && !palata.contains("1")) {
                                                                    if (!palata.contains("2-palata") && !palata.contains("2")) {
                                                                        if (!palata.contains("3-palata") && !palata.contains("3")) {
                                                                            if (!palata.contains("4-palata") && !palata.contains("4")) {
                                                                                if (!palata.contains("5-palata") && !palata.contains("5")) {
                                                                                    if (!palata.contains("6-palata") && !palata.contains("6")) {
                                                                                        if (!palata.contains("7-palata") && !palata.contains("7")) {
                                                                                            if (!palata.contains("8-palata") && !palata.contains("8")) {
                                                                                                if (palata.contains("9-palata") || palata.contains("9")) {
                                                                                                    patient.setWardNumber(9);
                                                                                                }
                                                                                                break;
                                                                                            }

                                                                                            patient.setWardNumber(8);
                                                                                            break;
                                                                                        }

                                                                                        patient.setWardNumber(7);
                                                                                        break;
                                                                                    }

                                                                                    patient.setWardNumber(6);
                                                                                    break;
                                                                                }

                                                                                patient.setWardNumber(5);
                                                                                break;
                                                                            }

                                                                            patient.setWardNumber(4);
                                                                            break;
                                                                        }

                                                                        patient.setWardNumber(3);
                                                                        break;
                                                                    }

                                                                    patient.setWardNumber(2);
                                                                    break;
                                                                }

                                                                patient.setWardNumber(1);
                                                                break;
                                                            }

                                                            patient.setWardNumber(10);
                                                            break;
                                                        }

                                                        patient.setWardNumber(11);
                                                        break;
                                                    }

                                                    patient.setWardNumber(12);
                                                    break;
                                                }

                                                patient.setWardNumber(13);
                                                break;
                                            }

                                            patient.setWardNumber(14);
                                            break;
                                        }

                                        patient.setWardNumber(15);
                                        break;
                                    }

                                    patient.setWardNumber(16);
                                    break;
                                }

                                patient.setWardNumber(0);
                                break;
                            case 2:
                                if (cellType.equals(CellType.NUMERIC) && cell.getNumericCellValue() == 0.0D) {
                                    patient.setFullName("Bo'sh");
                                    break;
                                }

                                patient.setFullName(cell.getStringCellValue());
                                break;
                            case 3:
                                switch (cell.getCellType()) {
                                    case NUMERIC:
                                        patient.setInDate(String.valueOf(cell.getNumericCellValue()));
                                        continue;
                                    case STRING:
                                        patient.setInDate(cell.getStringCellValue());
                                    default:
                                        continue;
                                }
                            case 4:
                                switch (cell.getCellType()) {
                                    case NUMERIC:
                                        patient.setOutDate(String.valueOf(cell.getNumericCellValue()));
                                        continue;
                                    case STRING:
                                        patient.setOutDate(cell.getStringCellValue());
                                    default:
                                        continue;
                                }
                            case 5:
                                String isPaid = cell.getStringCellValue();
                                if (isPaid.contains("pullik")) {
                                    patient.setIsPaid("pullik");
                                }

                                if (isPaid.contains("imtiyoz")) {
                                    patient.setIsPaid("imtiyoz");
                                }
                                break;
                            case 6:
                                if (!cellType.equals(CellType.BLANK)) {
                                    qavat = cell.getStringCellValue();
                                }

                                if (qavat.contains("1") || qavat.contains("1 - qavat") || qavat.contains("1-qavat") || qavat.contains("1- qavat") || qavat.contains("1 -qavat")) {
                                    patient.setFloor(1);
                                }

                                if (qavat.contains("2") || qavat.contains("2 - qavat") || qavat.contains("2-qavat") || qavat.contains("2- qavat") || qavat.contains("2 -qavat")) {
                                    patient.setFloor(2);
                                }

                                if (qavat.contains("3") || qavat.contains("3 - qavat") || qavat.contains("3-qavat") || qavat.contains("3- qavat") || qavat.contains("3 -qavat")) {
                                    patient.setFloor(3);
                                }
                        }
                    }
                    if (emptyCells < 4) {
                        patientList.add(patient);
                    }
                }
            }
            patientRepository.deleteAll();
            patientRepository.saveAll(patientList);
            sendMessage.setText("Ma'lumotlar omboriga bemorlar ro'yxati qo'shildi");
            return sendMessage;
        }catch (Exception e){
            sendMessage.setText("Xatolik yuz berdi");
            return sendMessage;
        }
    }

    public SendMessage savePrice(Message message, String name){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(message.getChatId()));

        try {

            List<Price> priceList = new ArrayList<>();

            FileInputStream fis = new FileInputStream("downloads/" + name);

            XSSFWorkbook wb = new XSSFWorkbook(fis);

            XSSFSheet sheet = wb.getSheetAt(0);

            for (Row row : sheet)     //iteration over row using for each loop
            {
                int emptyCells = 0;
                Price price = new Price();
                if (row.getRowNum() > 1) {
                    for (Cell cell : row) {
                        int columnIndex = cell.getColumnIndex();

                        CellType cellType = cell.getCellType();
                        if (cellType.equals(CellType._NONE) || cellType.equals(CellType.BLANK)) {
                            emptyCells++;
                            continue;
                        }

                                switch(columnIndex) {
                                    case 0:
                                        price.setSection(cell.getStringCellValue());
                                        break;
                                    case 1:
                                        price.setServiceName(cell.getStringCellValue());
                                        break;
                                    case 2:
                                        price.setToUzb(cell.getNumericCellValue());
                                        break;
                                    case 3:
                                        price.setToOther(cell.getNumericCellValue());
                                }

                    }
                    if (emptyCells < 3) {
                        priceList.add(price);
                    }
                }
            }
            priceRepository.deleteAll();
            priceRepository.saveAll(priceList);
            sendMessage.setText("Ma'lumotlar omboriga narxlar ro'yxati qo'shildi");
            return sendMessage;
        }catch (Exception e){
            sendMessage.setText("Xatolik yuz berdi");
            return sendMessage;
        }
    }

    public SendMessage saveFood(Message message) {
        return null;
    }

    public SendMessage saveDrugs(Message message) {
        return null;
    }



}
