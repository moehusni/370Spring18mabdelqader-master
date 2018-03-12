package edu.qc.seclass.tipcalculator;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TipCalculatorActivity extends AppCompatActivity {

    EditText mCheckAmountEditText;
    EditText mPartySizeValue;
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);

        /* Instantiating Toast */
        int duration = Toast.LENGTH_SHORT;
        Context context = getApplicationContext();
        CharSequence text = "Empty or incorrect value(s)";
        final Toast toast = Toast.makeText(context, text, duration);

        /* Buttons and Input acceptance */
        mButton = (Button) findViewById(R.id.buttonCompute);
        mPartySizeValue = (EditText) findViewById(R.id.partySizeValue);
        mCheckAmountEditText = (EditText) findViewById(R.id.checkAmountValue);

        /* Actions that occur when clicking the compute button */
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    computeTip();
                } catch (Exception e) {
                    toast.show();
                }
            }
        });

    }

    void computeTip() {
        /* Accept the Inputs from checkAmountValue and partySizeValue textViews */
        int checkAmt = Integer.parseInt(mCheckAmountEditText.getText().toString());
        int partySize = Integer.parseInt(mPartySizeValue.getText().toString());
        String prepend = "$";

        /* Calculate and output 15% tip */
        int tip15 = (int) Math.round((checkAmt * .15) / partySize);
        TextView Tip15 = (TextView) findViewById(R.id.fifteenPercentTipValue);
        prepend += tip15;
        Tip15.setText(prepend);

        /* Calculate and output 15% total */
        int total15 = checkAmt / partySize + tip15;
        TextView Total15 = (TextView) findViewById(R.id.fifteenPercentTotalValue);
        prepend = "$";
        prepend += total15;
        Total15.setText(prepend);

        /* Calculate and output 20% tip */
        int tip20 = (int) Math.round((checkAmt * .20) / partySize);
        TextView Tip20 = (TextView) findViewById(R.id.twentyPercentTipValue);
        prepend = "$";
        prepend += tip20;
        Tip20.setText(prepend);

        /* Calculate and output 20% total */
        int total20 = checkAmt / partySize + tip20;
        TextView Total20 = (TextView) findViewById(R.id.twentyPercentTotalValue);
        prepend = "$";
        prepend += total20;
        Total20.setText(prepend);

        /* Calculate and output 25% tip */
        int tip25 = (int) Math.round((checkAmt * .25) / partySize);
        TextView Tip25 = (TextView) findViewById(R.id.twentyfivePercentTipValue);
        prepend = "$";
        prepend += tip25;
        Tip25.setText(prepend);

        /* Calculate and output 25% total */
        int total25 = checkAmt / partySize + tip25;
        TextView Total25 = (TextView) findViewById(R.id.twentyfivePercentTotalValue);
        prepend = "$";
        prepend += total25;
        Total25.setText(prepend);
    }


}