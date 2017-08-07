require 'csv'


module Bank
  class Account
    def initialize(id, balance, open_date)
      raise ArgumentError, 'Balance cannot be negative' unless balance > 0

      @id = id
      @balance = balance
      @open_date = open_date
    end

    def balance()
      @balance
    end

    def deposit(amnt)
      @balance += amnt
    end

    def withdrawl(amnt)
      new_balance = @balance - amnt
      raise ArgumentError, 'Cant over draft' unless new_balance > 0
      @balance = new_balance
    end

    def self.all()
      ret = Array.new
      CSV.foreach('accounts.csv') do |row|
        ret.push(Account.new(row[0], row[1].to_i, row[2]))
      end
      ret
    end

  end

  class SavingsAccount < Account

    def initialize(id, balance, open_date)
      raise ArgumentError, 'Balance cannot be less than 10 ' unless balance >= 10

      @id = id
      @balance = balance
      @open_date = open_date
    end

    def withdrawl(amnt)
      new_balance = @balance - amnt
      if (new_balance < 10)
        print 'cant go below 10'
      else
        @balance = new_balance
      end
      @balance
    end

      def add_interest(rate)
      interest = @balance * rate/100
      @balance += interest
      @balance
    end

  end

  class Transaction

  end

  class Budget

  end

  class BudgetCategory

  end

end
