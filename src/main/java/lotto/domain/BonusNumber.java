package lotto.domain;

import lotto.validator.system.domain.BonusNumberValidator;
import lotto.validator.system.domain.WinningCombinationValidator;

public class BonusNumber {
    private final int bonusNumber;

    public BonusNumber(int bonusNumber){
        BonusNumberValidator.validateBonusNumber(bonusNumber);
        this.bonusNumber =bonusNumber;
    }

    public int getBonusNumber(){
        return bonusNumber;
    }
}
