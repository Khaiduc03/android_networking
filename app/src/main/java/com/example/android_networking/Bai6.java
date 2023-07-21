package com.example.android_networking;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Bai6 extends AppCompatActivity {
    //Khai báo biến
    private BankAccount account;
    private ExecutorService threadPool;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai6);

        //Ánh xạ
        TextInputEditText edtAmount = findViewById(R.id.textInputEdtAmount);
        Button btnDeposit = findViewById(R.id.btnDeposit);
        Button btnWithdraw = findViewById(R.id.btnWithdraw);
        Button btnQuery = findViewById(R.id.btnQuery);
        tvResult = findViewById(R.id.tvResult);

        //Khởi tạo một tài khoản ngân hàng
        account = new BankAccount("Khải", 50000);

        //Bắt sự kiện cho btnQuery (button Truy vấn)
        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvResult.setText("Tài khoản: " + account.getAccountNumber() + "\n"
                        + "Số dư: " + account.getBalance());
            }
        });

        //Bắt sự kiện cho btnDeposit (button Gửi)
        btnDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double amount = Double.parseDouble(edtAmount.getText().toString());
                Transaction transaction = new Transaction(account, amount, "deposit");
                threadPool.execute(transaction);
            }
        });

        //Bắt sự kiện cho btnWithdraw (button Rút)
        btnWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double amount = Double.parseDouble(edtAmount.getText().toString());
                Transaction transaction = new Transaction(account, amount, "withdraw");
                threadPool.execute(transaction);
            }
        });
        threadPool = Executors.newFixedThreadPool(2);
    }

    //Class Transaction (Lưu ý: Có thể tạo file Class riêng)
    private class Transaction implements Runnable {
        private BankAccount account;
        private double amount;
        private String transactionType;

        public Transaction(BankAccount account, double amount, String transactionType) {
            this.account = account;
            this.amount = amount;
            this.transactionType = transactionType;
        }

        @Override
        public void run() {
            if (transactionType.equals("deposit")) {
                account.deposit(amount);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvResult.setText("Deposit successful");
                    }
                });
            } else if (transactionType.equals("withdraw")) {
                double currentBalance = account.getBalance();
                if (currentBalance >= amount) {
                    account.withdraw(amount);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvResult.setText("Withdraw successfull");
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvResult.setText("Số tiền trong tài khoản không đủ." + "\n"
                                    + "Số dư hiện tại trong tài khoản là: " + currentBalance);
                        }
                    });
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        threadPool.shutdown();
    }
}
