/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package renzettialessandrozoltar;

import java.util.Random;

/**
 *
 * @author renzetti.alessandro
 */
public class GestoreDomande {

    public String tipo="";
    
    private final Random random = new Random();

    private final String[] saggio = {
        "Il tempo rivelerà la verità.",
        "Ascolta il tuo cuore, ma usa la testa.",
        "La pazienza è la chiave."
    };
    private final String[] sarcastico = {
        "NOOO, giura?",
        "Ti serviva una sfera di cristallo per questo?"
    };
    private final String[] buso = {
        "Eh noooooooooo",
        "I segni sono oscuri, viaggiatore.",
        "Il destino non vuole rivelarsi."
    };
    
    public String getRisposta(String domanda) {
        String[] pool = switch (tipo.toLowerCase()) {
        case "sarcastico" -> sarcastico;
        case "misterioso" -> buso;
        default -> saggio;
    };
        return pool[random.nextInt(pool.length)];
    }
    
}
