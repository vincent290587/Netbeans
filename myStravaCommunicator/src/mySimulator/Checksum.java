/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mySimulator;

/**
 *
 * @author vincent
 */
/**
 * Provides Sentence checksum calculation and utilities.
 *
 * @author Kimmo Tuukkanen
 */
public final class Checksum {
    
    static char CHECKSUM_DELIMITER = '*';

    public Checksum() {
    }

    /**
     * Append or replace existing checksum in specified NMEA sentence.
     *
     * @param nmea Sentence in String representation
     * @return The specified String with checksum added.
     */
    public static String add(String nmea) {
        String str = nmea.substring(0, index(nmea));
        String sum = calculate(str);
        return String.format("%s%c%s", str, CHECKSUM_DELIMITER, sum);
    }

    /**
     * Calculates checksum for given NMEA sentence, i.e. XOR of each character
     * between '$' and '*' characters (exclusive).
     *
     * @param nmea Sentence String with or without checksum.
     * @return Hexadecimal checksum
     */
    public static String calculate(String nmea) {
        return xor(nmea.substring(1, index(nmea)));
    }

    /**
     * Calculates XOR checksum of given String. Resulting hex value is returned
     * as a String in two digit format, padded with a leading zero if necessary.
     *
     * @param str String to calculate checksum for.
     * @return Hexadecimal checksum
     */
    public static String xor(String str) {
        int sum = 0;
        for (int i = 0; i < str.length(); i++) {
            sum ^= (byte) str.charAt(i);
        }
        return String.format("%02X", sum);
    }

    /**
     * Returns the index of checksum separator char in specified NMEA sentence.
     * If separator is not found, returns the String length.
     *
     * @param nmea Sentence String
     * @return Index of checksum separator or String length.
     */
    public static int index(String nmea) {
        return nmea.indexOf(CHECKSUM_DELIMITER) > 0
                ? nmea.indexOf(CHECKSUM_DELIMITER) : nmea.length();
    }
}
