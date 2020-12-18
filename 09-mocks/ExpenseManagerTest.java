package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.*;
import org.mockito.*;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import put.io.students.fancylibrary.database.FancyDatabase;
import put.io.students.fancylibrary.database.IFancyDatabase;
import put.io.students.fancylibrary.service.FancyService;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ExpenseManagerTest {
    @org.junit.jupiter.api.Test
    void TestCalculateTotal() {
        ExpenseRepository rep = mock(ExpenseRepository.class);
        Expense exp = new Expense();
        List<Expense> lista = new ArrayList<Expense>();
        lista.add(exp);
        lista.add(exp);
        lista.add(exp);
        when(rep.getExpenses()).thenReturn(lista);
        when(rep.getExpensesByCategory("Food")).thenReturn(Collections.emptyList());
        when(rep.getExpensesByCategory("Sport")).thenReturn(Collections.emptyList());
        when(rep.getExpensesByCategory("Home")).thenReturn(lista);
        when(rep.getExpensesByCategory("Car")).thenReturn(lista);
        ExpenseManager menager = new ExpenseManager(rep, new FancyService());

        assertEquals(menager.calculateTotal(), 0);
        assertEquals(menager.calculateTotalForCategory("Home"), 0);
        verify(rep).getExpensesByCategory(eq("Home"));

        assertEquals(menager.calculateTotalForCategory("Car"), 0);
        verify(rep).getExpensesByCategory(eq("Car"));

        assertEquals(menager.calculateTotalForCategory("Sport"), 0);
        verify(rep).getExpensesByCategory(eq("Car"));
    }
    @org.junit.jupiter.api.Test

    void TestDollar() throws ConnectException{
        ExpenseRepository rep = mock(ExpenseRepository.class);
        FancyService ser = mock(FancyService.class);
        Expense exp = new Expense();
        rep.addExpense(exp);
        ExpenseManager menager = new ExpenseManager(rep, ser);
        //when(ser.convert()).thenThrow(ConnectException)

        when(ser.convert(anyDouble(), eq("PLN"), eq("USD"))).thenAnswer(
                new Answer() {
                    public Object answer(InvocationOnMock invocation) {
                        Object[] args = invocation.getArguments();
                        Object mock = invocation.getMock();
                        Double a = (Double) args[0];

                        //System.out.println((Arrays.toString(args))[0]);
                        return 4*a;
                    }
                });

        menager.calculateTotalInDollars();
        verify(ser).convert(anyDouble(), eq("PLN"), eq("USD"));
        when(ser.convert(4,"PLN", "USD")).thenReturn(16.0);
        assertEquals(ser.convert(4,"PLN", "USD"),16.0);
        when(ser.convert(anyDouble(), eq("PLN"), eq("USD"))).thenThrow(ConnectException.class);
        assertThrows(ConnectException.class, ()->{ser.convert(4, "PLN", "USD");});

    }

}
