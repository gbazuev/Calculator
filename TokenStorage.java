package HWm4t6n2.src.java;

import java.util.ArrayList;

public class TokenStorage {
    public ArrayList<Character> operators = new ArrayList<>();
    public ArrayList<Integer> operands = new ArrayList<>();

    public int[] getOperands()  {
        int currentOperatorIndex = getOperatorIndex();
        return new int[]{operands.get(currentOperatorIndex), operands.get(currentOperatorIndex + 1)};
    }

    public char getOperator()   {
        return operators.get(getOperatorIndex());
    }

    public void replaceOperands(int result)    {
        int currentOperatorIndex = getOperatorIndex();
        operands.remove(currentOperatorIndex);
        operands.remove(currentOperatorIndex);
        operands.add(currentOperatorIndex, result);
    }

    public void deleteOperator()    {
        operators.remove(getOperatorIndex());
    }

    private int getOperatorIndex()    {
        for (int index = 0; index < operators.size(); index++)   {
            if (operators.get(index) == '*' || operators.get(index) == '/') {
                return index;
            }
        }
        return 0;
    }
}
