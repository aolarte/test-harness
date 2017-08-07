# spec/string_calculator_spec.rb
require_relative('bank')

describe Bank::Account do
  describe "#new" do
    context "given a positive balance" do
      it "returns the balance" do
        expect(Bank::Account.new(1,10, DateTime.now).balance).to eql(10)
      end
    end

    context "given a negative balance" do
      it "throw exception" do
        expect{Bank::Account.new(1,-10, DateTime.now)}.to raise_error(ArgumentError)
      end
    end

    context "makes a deposit" do
      it "returns the balance" do
        expect(Bank::Account.new(1,10, DateTime.now).deposit(5)).to eql(15)
      end
    end

    context "makes a withdrawl" do
      it "returns the balance" do
        expect(Bank::Account.new(1,10, DateTime.now).withdrawl(2)).to eql(8)
      end
    end

    context "makes a withdrawl" do
      it "wont overdraft" do
        expect{Bank::Account.new(1,10, DateTime.now).withdrawl(15)}.to raise_error(ArgumentError)
      end
    end

    context "Load csv" do
      it "12 accounts" do
        expect(Bank::Account.all.size).to eql(12)
      end
    end

  end
end

describe Bank::SavingsAccount do
  describe "#new" do
    context "given a positive balance" do
      it "returns the balance" do
        expect(Bank::SavingsAccount.new(1,10, DateTime.now).balance).to eql(10)
      end
    end

    context "given a balance less than 10" do
      it "throw exception" do
        expect{Bank::SavingsAccount.new(1,-9, DateTime.now)}.to raise_error(ArgumentError)
      end
    end


    context "makes a withdrawl" do
      it "returns the balance" do
        expect(Bank::SavingsAccount.new(1,12, DateTime.now).withdrawl(3)).to eql(12)
      end
    end

    context "add interest rate" do
      it "calculates rate" do
        expect(Bank::SavingsAccount.new(1,100,DateTime.now).add_interest(0.25)).to eql(100.25)
      end
    end

  end
end