/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.utility;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author vfmaz
 */
public class Menssagens {

    public static void info(String texto) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, texto, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public static void erro(String texto) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, texto, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public static void aviso(String texto) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, texto, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
