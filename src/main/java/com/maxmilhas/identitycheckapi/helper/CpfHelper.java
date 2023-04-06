package com.maxmilhas.identitycheckapi.helper;

import com.maxmilhas.identitycheckapi.dto.CpfDTO;
import com.maxmilhas.identitycheckapi.entity.Cpf;
import org.springframework.stereotype.Component;

@Component
public class CpfHelper {
    public void convertDTOtoEntity(Cpf entity, CpfDTO dto){
        entity.setCpf(dto.getCpf());
    }
    public static boolean isValid(String cpf) {
        if (cpf == null || cpf.length() != 11 || cpf.chars().allMatch(character -> character == cpf.charAt(0))) {
            return false;
        }

        int[] cpfNumbers = new int[11];

        for (int index = 0; index < 11; index++) {
            char currentChar = cpf.charAt(index);
            if (Character.isDigit(currentChar)) {
                cpfNumbers[index] = currentChar - '0';
            } else {
                return false;
            }
        }

        int firstSum = 0;
        for (int index = 0; index < 9; index++) {
            firstSum += (10 - index) * cpfNumbers[index];
        }

        int secondSum = 0;
        for (int index = 0; index < 10; index++) {
            secondSum += (11 - index) * cpfNumbers[index];
        }

        int firstMod = firstSum % 11;
        int secondMod = secondSum % 11;

        int firstVerificationDigit = (firstMod < 2) ? 0 : 11 - firstMod;
        int secondVerificationDigit = (secondMod < 2) ? 0 : 11 - secondMod;

        return firstVerificationDigit == cpfNumbers[9] && secondVerificationDigit == cpfNumbers[10];
    }
}
