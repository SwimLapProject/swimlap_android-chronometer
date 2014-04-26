/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.data;


import java.util.HashMap;

public class RaceData {
    private HashMap<Integer, String> styles;
    private HashMap<Integer, Integer> distances;
    private HashMap<Integer, String> genders;

    public RaceData() {
        styles = new HashMap<Integer, String>();
        distances = new HashMap<Integer, Integer>();
        genders = new HashMap<Integer, String>();
        makeData();
    }

    public String giveStyle(int raceId) {
        return styles.get(raceId);
    }

    public int giveDistance(int raceId) {
        return distances.get(raceId);
    }

    public String giveGender(int raceID) {
        return genders.get(raceID);
    }

    public void makeData() {

        distances.put(100, 25);
        styles.put(100, "FREE");
        distances.put(150, 25);
        styles.put(150, "FREE");
        distances.put(200, 25);
        styles.put(200, "FREE");

        genders.put(100, "-M");
        genders.put(150, "-F");
        genders.put(200, "-M-F");

        distances.put(1, 50);
        styles.put(1, "FREE");
        distances.put(51, 50);
        styles.put(51, "FREE");
        distances.put(201, 50);
        styles.put(201, "FREE");

        genders.put(1, "-M");
        genders.put(51, "-F");
        genders.put(201, "-M-F");

        distances.put(2, 100);
        styles.put(2, "FREE");
        distances.put(52, 100);
        styles.put(52, "FREE");
        distances.put(202, 100);
        styles.put(202, "FREE");

        genders.put(2, "-M");
        genders.put(52, "-F");
        genders.put(202, "-M-F");

        distances.put(3, 200);
        styles.put(3, "FREE");
        distances.put(53, 200);
        styles.put(53, "FREE");
        distances.put(203, 200);
        styles.put(203, "FREE");

        genders.put(3, "-M");
        genders.put(53, "-F");
        genders.put(203, "-M-F");

        distances.put(4, 400);
        styles.put(4, "FREE");
        distances.put(54, 400);
        styles.put(54, "FREE");
        distances.put(204, 400);
        styles.put(204, "FREE");

        genders.put(4, "-M");
        genders.put(54, "-F");
        genders.put(204, "-M-F");

        distances.put(5, 800);
        styles.put(5, "FREE");
        distances.put(55, 800);
        styles.put(55, "FREE");
        distances.put(205, 800);
        styles.put(205, "FREE");

        genders.put(5, "-M");
        genders.put(55, "-F");
        genders.put(205, "-M-F");

        distances.put(7, 1000);
        styles.put(7, "FREE");
        distances.put(57, 1000);
        styles.put(57, "FREE");
        distances.put(207, 1000);
        styles.put(207, "FREE");

        genders.put(7, "-M");
        genders.put(57, "-F");
        genders.put(207, "-M-F");

        distances.put(6, 1500);
        styles.put(6, "FREE");
        distances.put(56, 1500);
        styles.put(56, "FREE");
        distances.put(206, 1500);
        styles.put(206, "FREE");

        genders.put(6, "-M");
        genders.put(56, "-F");
        genders.put(206, "-M-F");

        distances.put(16, 3000);
        styles.put(16, "FREE");
        distances.put(66, 3000);
        styles.put(66, "FREE");
        distances.put(216, 3000);
        styles.put(216, "FREE");

        genders.put(16, "-M");
        genders.put(66, "-F");
        genders.put(216, "-M-F");

        distances.put(15, 5000);
        styles.put(15, "FREE");
        distances.put(65, 5000);
        styles.put(65, "FREE");
        distances.put(215, 5000);
        styles.put(215, "FREE");

        genders.put(15, "-M");
        genders.put(65, "-F");
        genders.put(215, "-M-F");

        distances.put(110, 25);
        styles.put(110, "BACK");
        distances.put(160, 25);
        styles.put(160, "BACK");
        distances.put(210, 25);
        styles.put(210, "BACK");

        genders.put(110, "-M");
        genders.put(160, "-F");
        genders.put(210, "-M-F");

        distances.put(11, 50);
        styles.put(11, "BACK");
        distances.put(61, 50);
        styles.put(61, "BACK");
        distances.put(211, 50);
        styles.put(211, "BACK");

        genders.put(11, "-M");
        genders.put(61, "-F");
        genders.put(211, "-M-F");

        distances.put(12, 100);
        styles.put(12, "BACK");
        distances.put(62, 100);
        styles.put(62, "BACK");
        distances.put(212, 100);
        styles.put(212, "BACK");

        genders.put(12, "-M");
        genders.put(62, "-F");
        genders.put(212, "-M-F");

        distances.put(13, 200);
        styles.put(13, "BACK");
        distances.put(63, 200);
        styles.put(63, "BACK");
        distances.put(213, 200);
        styles.put(213, "BACK");

        genders.put(13, "-M");
        genders.put(63, "-F");
        genders.put(213, "-M-F");

        distances.put(120, 25);
        styles.put(120, "BREA");
        distances.put(170, 25);
        styles.put(170, "BREA");
        distances.put(220, 25);
        styles.put(220, "BREA");

        genders.put(120, "-M");
        genders.put(170, "-F");
        genders.put(220, "-M-F");

        distances.put(21, 50);
        styles.put(21, "BREA");
        distances.put(71, 50);
        styles.put(71, "BREA");
        distances.put(221, 50);
        styles.put(221, "BREA");

        genders.put(21, "-M");
        genders.put(71, "-F");
        genders.put(221, "-M-F");

        distances.put(22, 100);
        styles.put(22, "BREA");
        distances.put(72, 100);
        styles.put(72, "BREA");
        distances.put(222, 100);
        styles.put(222, "BREA");

        genders.put(22, "-M");
        genders.put(72, "-F");
        genders.put(222, "-M-F");

        distances.put(23, 200);
        styles.put(23, "BREA");
        distances.put(73, 200);
        styles.put(73, "BREA");
        distances.put(223, 200);
        styles.put(223, "BREA");

        genders.put(23, "-M");
        genders.put(73, "-F");
        genders.put(223, "-M-F");

        distances.put(130, 25);
        styles.put(130, "BUTT");
        distances.put(180, 25);
        styles.put(180, "BUTT");
        distances.put(230, 25);
        styles.put(230, "BUTT");

        genders.put(130, "-M");
        genders.put(180, "-F");
        genders.put(230, "-M-F");

        distances.put(31, 50);
        styles.put(31, "BUTT");
        distances.put(81, 50);
        styles.put(81, "BUTT");
        distances.put(231, 50);
        styles.put(231, "BUTT");

        genders.put(31, "-M");
        genders.put(81, "-F");
        genders.put(231, "-M-F");


        distances.put(32, 100);
        styles.put(32, "BUTT");
        distances.put(82, 100);
        styles.put(82, "BUTT");
        distances.put(232, 100);
        styles.put(232, "BUTT");

        genders.put(32, "-M");
        genders.put(82, "-F");
        genders.put(232, "-M-F");

        distances.put(33, 200);
        styles.put(33, "BUTT");
        distances.put(83, 200);
        styles.put(83, "BUTT");
        distances.put(233, 200);
        styles.put(233, "BUTT");

        genders.put(33, "-M");
        genders.put(83, "-F");
        genders.put(233, "-M-F");

        distances.put(40, 100);
        styles.put(40, "MEDLL");
        distances.put(90, 100);
        styles.put(90, "MEDL");
        distances.put(240, 100);
        styles.put(240, "MEDL");

        genders.put(40, "-M");
        genders.put(90, "-F");
        genders.put(240, "-M-F");

        distances.put(41, 200);
        styles.put(41, "MEDL");
        distances.put(91, 200);
        styles.put(91, "MEDL");
        distances.put(241, 200);
        styles.put(241, "MEDL");

        genders.put(41, "-M");
        genders.put(91, "-F");
        genders.put(241, "-M-F");

        distances.put(42, 400);
        styles.put(42, "MEDL");
        distances.put(92, 400);
        styles.put(92, "MEDL");
        distances.put(242, 400);
        styles.put(242, "MEDL");

        genders.put(42, "-M");
        genders.put(92, "-F");
        genders.put(242, "-M-F");

        distances.put(8, 100);
        styles.put(8, "4x25_NL");
        distances.put(58, 100);
        styles.put(58, "4x25_NL");
        distances.put(86, 100);
        styles.put(86, "4x25_NL");

        genders.put(8, "-M");
        genders.put(58, "-F");
        genders.put(86, "-M-F");

        distances.put(47, 100);
        styles.put(47, "4x50_NL");
        distances.put(97, 200);
        styles.put(97, "4x50_NL");
        distances.put(87, 200);
        styles.put(87, "4x50_NL");

        genders.put(47, "-M");
        genders.put(97, "-F");
        genders.put(87, "-M-F");

        distances.put(43, 400);
        styles.put(43, "4x100_NL");
        distances.put(93, 400);
        styles.put(93, "4x100_NL");
        distances.put(88, 400);
        styles.put(88, "4x100_NL");

        genders.put(43, "-M");
        genders.put(93, "-F");
        genders.put(88, "-M-F");

        distances.put(44, 800);
        styles.put(44, "4x200_NL");
        distances.put(94, 800);
        styles.put(94, "4x200_FREE");
        distances.put(34, 800);
        styles.put(34, "4x200_FREE");

        genders.put(44, "-M");
        genders.put(94, "-F");
        genders.put(34, "-M-F");

        distances.put(111, 200);
        styles.put(111, "4x50_BACK");
        distances.put(161, 200);
        styles.put(161, "4x50_BACK");

        genders.put(111, "-M");
        genders.put(161, "-F");

        distances.put(39, 100);
        styles.put(39, "4x25_MED");
        distances.put(89, 100);
        styles.put(89, "4x25_MED");
        distances.put(38, 100);
        styles.put(38, "4x25_MED");

        genders.put(39, "-M");
        genders.put(89, "-F");
        genders.put(38, "-M-F");


        distances.put(121, 200);
        styles.put(121, "4x50_BRE");
        distances.put(171, 200);
        styles.put(171, "4x50_BRE");

        genders.put(121, "-M");
        genders.put(171, "-F");

        distances.put(48, 200);
        styles.put(48, "4x50_MED");
        distances.put(98, 200);
        styles.put(98, "4x50_MED");
        distances.put(37, 200);
        styles.put(37, "4x50_MED");

        genders.put(48, "-M");
        genders.put(98, "-F");
        genders.put(37, "-M-F");

        distances.put(131, 200);
        styles.put(131, "4x50_BUT");
        distances.put(181, 200);
        styles.put(181, "4x50_BUT");

        genders.put(131, "-M");
        genders.put(181, "-F");

        distances.put(49, 300);
        styles.put(79, "6x50_FRE");
        distances.put(99, 300);
        styles.put(99, "6x50_FRE");
        distances.put(35, 300);
        styles.put(35, "6x50_FRE");

        genders.put(49, "-M");
        genders.put(99, "-F");
        genders.put(35, "-M-F");

        distances.put(46, 400);
        styles.put(46, "4x100_MED");
        distances.put(96, 400);
        styles.put(96, "4x100_MED");
        distances.put(36, 400);
        styles.put(36, "4x100_MED");

        genders.put(46, "-M");
        genders.put(96, "-F");
        genders.put(36, "-M-F");

        distances.put(9, 500);
        styles.put(9, "10x50_FRE");
        distances.put(59, 500);
        styles.put(59, "10x50_FRE");
        distances.put(84, 500);
        styles.put(84, "10x50_FRE");

        genders.put(9, "-M");
        genders.put(59, "-F");
        genders.put(84, "-M-F");

        distances.put(14, 800);
        styles.put(14, "8x100_FRE");
        distances.put(64, 800);
        styles.put(67, "8x100_FRE");
        distances.put(214, 800);
        styles.put(214, "8x100_FRE");

        genders.put(14, "-M");
        genders.put(64, "-F");
        genders.put(214, "-M-F");

        distances.put(45, 1000);
        styles.put(45, "10x100_FRE");
        distances.put(95, 1000);
        styles.put(95, "10x100_FRE");
        distances.put(85, 1000);
        styles.put(85, "10x100_FRE");

        genders.put(45, "-M");
        genders.put(95, "-F");
        genders.put(85, "-M-F");
    }
}
