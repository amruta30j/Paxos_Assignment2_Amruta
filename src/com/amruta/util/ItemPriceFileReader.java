package com.amruta.util;

import com.amruta.exception.FileReaderException;
import com.amruta.model.Item;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

/**
 * Created by amrutaj on 12/12/2018.
 */
public class ItemPriceFileReader {

    private String fileName;

    public ItemPriceFileReader(String fileName) {
        this.fileName = fileName;
    }

    /** This method reads the file and writes
     * the data to the treemap
     * @return
     * @throws IOException
     * @throws FileReaderException
     */
    public TreeMap<Integer, Item> readFile() throws IOException, FileReaderException {

        TreeMap<Integer, Item> itemPricesMap = new TreeMap<>();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        String currentLine;

        try {
            final File file = new File(getFileName());
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            while ((currentLine = bufferedReader.readLine()) != null) {
                Item item = ItemMapper.mapItems(currentLine);
                itemPricesMap.put(item.getItemPrice(), item);
            }
        } catch (IOException ex) {
            throw new FileReaderException("Error while reading file " + fileName, ex);
        } finally {
            fileReader.close();
            bufferedReader.close();
        }
        return itemPricesMap;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
