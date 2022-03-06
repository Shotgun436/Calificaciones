package labproyti6.funciona.calcprom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TableLayout tableLayout;
    private EditText txtUaprend;
    private EditText txtPrimerParc;
    private EditText txtSegundoParc;
    private EditText txtTercerParc;
    private String[]header={"Ud. de Aprend.","1er.Parc.","2o.Parc.","3er.Parc.","Prom.","Observaciones"};
    private ArrayList<String[]> rows=new ArrayList<>();
    private TablaDinamica tablaDinamica;
    private double prom1;
    private double prom2;
    private double prom3;
    private double promtot;
    private int cuentamaterias=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tableLayout=(TableLayout) findViewById(R.id.table);
        txtUaprend=(EditText) findViewById(R.id.txtUaprend);
        txtPrimerParc=(EditText) findViewById(R.id.txtPrimerParc);
        txtSegundoParc=(EditText) findViewById(R.id.txtSegundoParc);
        txtTercerParc=(EditText) findViewById(R.id.txtTercerParc);

        setTablaDinamica(new TablaDinamica(tableLayout,getApplicationContext()));
        getTablaDinamica().addHeader(header);
        //getTablaDinamica().addData(getClients());

    }
    public void save(View view){
        double prom=0;
        String comentario="";
        prom = (Double.parseDouble(txtPrimerParc.getText().toString())+
                Double.parseDouble(txtSegundoParc.getText().toString())+
                Double.parseDouble(txtTercerParc.getText().toString()))/3;
        if (prom <= 5.9) { comentario="extraordinario";}
        String[] item=new String[]{txtUaprend.getText().toString(),txtPrimerParc.getText().toString(),txtSegundoParc.getText().toString(),txtTercerParc.getText().toString(),String.format("%.1f", prom),comentario.toString()};
        getTablaDinamica().addItems(item);
        prom1 = Double.parseDouble(txtPrimerParc.getText().toString()) + prom1;
        prom2 = Double.parseDouble(txtSegundoParc.getText().toString()) + prom2;
        prom3 = Double.parseDouble(txtTercerParc.getText().toString()) + prom3;
        promtot += prom;
        cuentamaterias++;
        txtUaprend.setText("");
        txtPrimerParc.setText("");
        txtSegundoParc.setText("");
        txtTercerParc.setText("");
    }

    public void promedia(View view){
        prom1 = prom1 / cuentamaterias;
        prom2 = prom2 / cuentamaterias;
        prom3 = prom3 / cuentamaterias;
        promtot = promtot / cuentamaterias;
        String[] total=new String[]{"Promedio Final", String.format("%.1f", prom1), String.format("%.1f", prom2),String.format("%.1f", prom3),String.format("%.1f", promtot),""};
        getTablaDinamica().addItems(total);
        txtUaprend.setEnabled(false) ;
        txtPrimerParc.setEnabled(false);
        txtSegundoParc.setEnabled(false);
        txtTercerParc.setEnabled(false);

    }

    public void restablece(View view){
        txtUaprend.setEnabled(true) ;
        txtPrimerParc.setEnabled(true);
        txtSegundoParc.setEnabled(true);
        txtTercerParc.setEnabled(true);
        tableLayout.removeAllViews();
        getTablaDinamica().addHeader(header);
        prom1=0.0;
        prom2=0.0;
        prom3=0.0;
        promtot=0.0;
        cuentamaterias=0;


    }



    public TablaDinamica getTablaDinamica() {
        return tablaDinamica;
    }

    public void setTablaDinamica(TablaDinamica tablaDinamica) {
        this.tablaDinamica = tablaDinamica;
    }
}