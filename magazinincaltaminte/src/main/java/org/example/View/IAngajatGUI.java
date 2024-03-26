package org.example.View;

public interface IAngajatGUI extends IGUI{

    int getProdusId();

    void setProdusId(Integer id);

    String getProdusDenumire();

    void setProdusDenumire(String denumire);

    String getProdusProducator();

    void setProdusProducator(String producator);

    float getProdusPret();

    void setProdusPret(double pret);

    void setProdusPartIdM(Integer id);

    int getProdusPartId();

    String getProdusPartCuloare();

    int getProdusPartMarime();

    int getProdusPartDisponibilitate();

    int getProdusPartIdM();

    void setProdusPartCuloare(String culoare);

    void setProdusPartMarime(Integer marime);

    void setProdusPartDisponibilitate(Integer disponibilitate);
    int getIdMSelectat();
    String getFilter();
    String getFilterT();

    void setProdusPartId(int i);
}
