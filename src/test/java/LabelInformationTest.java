import backend.model.builder.item.Item;
import backend.model.builder.item.ItemUsableBuilder;
import org.junit.Before;
import org.junit.Test;
import frontend.view.LabelInformation;
import rules.interfaces.ICoordinate;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class LabelInformationTest {

    private final LabelInformation labelInformation = new LabelInformation();
    private Item item;
    private final String [] label =
            {"Capacidade do inventario 0/10","Nome","Peso","Descrição"};

    @Before
    public void create(){
        for (String s : label) {
            labelInformation.create(s);
        }
        item = ItemUsableBuilder.builder().localUse("praia").name("pa").description("ferramenta usada para cavar").weight(0)
                .coordinate(ICoordinate.getInstance(200,280)).image(null).removable(true).visible(true).build();
    }

    @Test
    public void createAllLabelValid(){
        assertEquals(label.length,labelInformation.getInfoLabel().length);
    }

    @Test
    public void validAllNameLabel(){
        String name;
        for (int i = 0; i < label.length; i++) {
            name = labelInformation.getInfoLabel()[i].getText();
            assertEquals(label[i],name);
        }
    }

    @Test
    public void validUpdateText(){
        var labelItem = new ArrayList<String>();
        labelItem.add(label[0]);
        labelItem.add(label[1] +": "+ item.getName());
        labelItem.add(label[2] +": "+ item.getWeight());
        labelItem.add("<html>"+label[3] +": "+ item.getDescription()+"</html>");
        labelInformation.updateText(item);
        for (int i = 0; i < labelInformation.getInfoLabel().length; i++) {
            assertEquals(labelItem.get(i),labelInformation.getInfoLabel()[i].getText());
        }
    }
    @Test
    public void validResetText(){
        labelInformation.updateText(item);
        labelInformation.resetText();
        String name;
        for (int i = 0; i < label.length; i++) {
            name = labelInformation.getInfoLabel()[i].getText();
            assertEquals(label[i],name);
        }
    }
}
