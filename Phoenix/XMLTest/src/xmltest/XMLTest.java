/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmltest;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author phoenix
 */
public class XMLTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws PropertyException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(Employee.class);

        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        Employee object = new Employee();
        object.setCode("CA");
        object.setName("Cath");
        object.setSalary(300);

        m.marshal(object, System.out);
    }

    @XmlRootElement
    static class Employee {

        private String code;

        private String name;

        private int salary;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSalary() {
            return salary;
        }

        public void setSalary(int population) {
            this.salary = population;
        }
    }
}
