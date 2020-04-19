package guru.springframework;

import java.util.Objects;

public class Money implements Expression {

    protected final String currency;
    protected final int amount;

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    @Override
    public Expression times(int multiplier){
        return new Money(amount * multiplier, currency);
    }

    public static Money dollar(int amount){
        return new Money(amount, "USD");
    }

    public static Money franc(int amount){
        return new Money(amount, "CHF");
    }

    protected String currency(){
        return currency;
    }

    @Override
    public Expression plus(Expression addend){
        return new Sum(this, addend);
    }

    @Override
    public Money reduce(Bank bank, String to){
        return new Money(amount / bank.rate(this.currency, to), to);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount == money.amount &&
                Objects.equals(currency, money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, amount);
    }

    @Override
    public String toString() {
        return "Money{" +
                "currency='" + currency + '\'' +
                ", amount=" + amount +
                '}';
    }
}
