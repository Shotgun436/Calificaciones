package labproyti6.funciona.calcprom;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class TablaDinamica {

        private final TableLayout tableLayout;
        private final Context context;
        private String[]header;
        private ArrayList<String[]>data;
        private TableRow tableRow;
        private TextView txtCell;
        private int indexC;

    public TablaDinamica(TableLayout tableLayout, Context context) {
            this.tableLayout=tableLayout;
            this.context=context;
        }
        public void addHeader(String[]header){
            this.header=header;
            createHeader();
        }
        public void addData(ArrayList<String[]>data){
            this.data=data;
            createDataTable();
        }
        public void newRow(){
            tableRow=new TableRow(context);
        }

        private void newCell(){
            txtCell=new TextView(context);
            txtCell.setGravity(Gravity.CENTER);
            txtCell.setTextSize(14);
        }
        private void createHeader(){
            indexC=0;
            newRow();
            while (indexC<header.length){
                newCell();
                txtCell.setText(header[indexC++]);
                tableRow.addView(txtCell, newTableRowParams());
            }
            tableLayout.addView(tableRow);
        }
        private void createDataTable(){
            String info;
            int indexR;
            for(indexR=1; indexR<=data.size();indexR++){
                newRow();
                for(indexC=0;indexC<header.length ;indexC++){
                    newCell();
                    String[] row=data.get(indexR-1);
                    info=(indexC<row.length)?row[indexC]:"";
                    txtCell.setText(info);
                    tableRow.addView(txtCell,newTableRowParams());
                }
                tableLayout.addView(tableRow);

            }
        }
        public void addItems(String[]item){
            String info;
            //data.add(item);
            int indexC=0;
            newRow();
            while(indexC<header.length){
                newCell();
                info=(indexC<header.length)?item[indexC++]:"";
                txtCell.setText(info);
                tableRow.addView(txtCell,newTableRowParams());
            }
            tableLayout.addView(tableRow);
        }


        private TableRow.LayoutParams newTableRowParams(){
            TableRow.LayoutParams params=new TableRow.LayoutParams();
            params.setMargins( 1, 1, 1, 1);
            params.weight=1;
            return params;

        }


}
