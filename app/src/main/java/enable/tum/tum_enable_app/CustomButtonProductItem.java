package enable.tum.tum_enable_app;

import android.content.Context;
import android.content.res.TypedArray;
import android.nfc.Tag;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Lennart Mittag on 06.12.2015.
 */
public class CustomButtonProductItem extends LinearLayout {
    LinearLayout containerCustomButtonProductItem;

    private Button txtOben;
    private TextView txtUnten;
    private ImageButton imgBtn;

    private String productName;
    private double price;
    private int backgroundResourceIdentifier;

    public CustomButtonProductItem(Context context) {
        super(context);
        initializeLayout(context);
    }

    public CustomButtonProductItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeLayout(context);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.CustomButton, 0, 0);

        String productName = a.getString(R.styleable.CustomButton_product_name);
        double price = (double) a.getFloat(R.styleable.CustomButton_product_price, 0.0f);
        int imgIdentifier = a.getResourceId(R.styleable.CustomButton_img_identifier, 0);

        txtOben.setText(productName);
        txtOben.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Test", "Inforequested");
                performClick();
            }
        });
        txtUnten.setText("Preis: " + String.format("%1$,.2f", price));
        imgBtn.setImageResource(imgIdentifier);
        imgBtn.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imgBtn.setAdjustViewBounds(true);
        imgBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                performClick();
            }
        });
    }

    public CustomButtonProductItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeLayout(context);
    }

    private void initializeLayout(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            inflater.inflate(R.layout.custom_button_product_item, this);
        }

        containerCustomButtonProductItem = (LinearLayout) findViewById(R.id.container_custom_button_product_item);
        txtOben = (Button) findViewById(R.id.txtOben);
        txtUnten = (TextView) findViewById(R.id.txtUnten);
        imgBtn = (ImageButton) findViewById(R.id.imageButtonProduct);
    }

    public void setPrice(double price) {
        this.price = price;
        txtUnten.setText("Preis: " + String.format("%1$,.2f", price) + "€");
    }

    public void setIdentifier(int id) {
        this.backgroundResourceIdentifier = id;
        imgBtn.setImageResource(id);
        imgBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                performClick();
            }
        });
    }

    public void setProductName(String productName) {
        this.productName = productName;
        txtOben.setText(productName);
    }
}
