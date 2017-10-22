/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ha.message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Buddhini
 */
public class MessageHolder implements Serializable
{
    public static class Message implements Serializable
    {
        private String category;
        private String key;
        private String bundle;
        private Object[] arguments;

        public Message(String key, String bundle, Object[] arguments)
        {
            this.key = key;
            this.bundle = bundle;
            this.arguments = arguments;
        }

        public Message(String category, String key, String bundle, Object[] arguments)
        {
            this.category = category;
            this.key = key;
            this.bundle = bundle;
            this.arguments = arguments;
        }

        public String getCategory()
        {
            return category;
        }

        public void setCategory(String category)
        {
            this.category = category;
        }

        public String getKey()
        {
            return key;
        }

        public String getBundle()
        {
            return bundle;
        }

        public Object[] getArguments()
        {
            return arguments;
        }
    }

    /**
     * HashMap containing key/message pairs representing the error messages.
     */
    private List<Message> errors = null;

    /**
     * HashMap containing key/message pairs representing the information messages.
     */
    private List<Message> informations = null;

    /**
     * Default constructor when no locale is needed (no internationalization) or
     * if the locale is not known at class instance create time.
     */
    public MessageHolder()
    {
        errors = new ArrayList();
        informations = new ArrayList();
    }

    /**
     * Default constructor when no locale is needed (no internationalization) or
     * if the locale is not known at class instance create time.
     */
    public MessageHolder(String category, String key, String bundle, Object[] arguments, boolean message)
    {
        errors = new ArrayList();
        informations = new ArrayList();
        if (message)
        {
            addInformation(category, key, bundle, arguments);
        }
        else
        {
            addError(category, key, bundle, arguments);
        }
    }

    /**
     * Returns a boolean indicating if there are any information messages stored
     * (with or without keys).
     * @return true if there are any information messages available
     */
    public boolean hasInformations()
    {
        return ((getInformations().size() > 0));
    }

    /**
     * Returns a boolean indicating if there are any error messages stored
     * (with or without keys).
     * @return true if there are any error messages available
     */
    public boolean hasErrors()
    {
        return ((getErrors().size() > 0));
    }

    public void addError(Message message, int index)
    {
        errors.add(index, message);
    }

    public void addInformation(Message message, int index)
    {
        informations.add(index, message);
    }

    /**
     * Adds a information message where the key is not of interest (internally this is
     * stored in the map with the given key as a key). This method is usally used
     * together with getInformationMessages()
     *
     * @param key representing the key field in a message
     * @param bundle Name of the i18n bundle that corresponds to this message
     * @param arguments the parametrized part of a message
     */
    public void addInformation(String key, String bundle, Object arguments[])
    {
        informations.add(new Message(key, bundle, arguments));
    }

    public void addInformation(String key, String bundle, Object arguments[], int index)
    {
        informations.add(index, new Message(key, bundle, arguments));
    }

    /**
     * Adds a information message where the key is not of interest (internally this is
     * stored in the map with the given key as a key). This method is usally used
     * together with getInformationMessages()
     *
     * @param category representing the category e.g. form field of the mesage
     * @param key representing the key field in a message
     * @param bundle Name of the i18n bundle that corresponds to this message
     * @param arguments the parametrized part of a message
     */
    public void addInformation(String category, String key, String bundle, Object arguments[])
    {
        informations.add(new Message(category, key, bundle, arguments));
    }

    public void addInformation(String category, String key, String bundle, Object arguments[], int index)
    {
        informations.add(index, new Message(category, key, bundle, arguments));
    }

    /**
     * Adds a error message where the key is not of interest (internally this is
     * stored in the map with the given key as a key). This method is usally used
     * together with getInformationMessages()
     *
     * @param key representing the key field in a message
     * @param bundle The i18n bundle name
     * @param arguments the parametrized part of a message
     */
    public void addError(String key, String bundle, Object arguments[])
    {
        errors.add(new Message(key, bundle, arguments));
    }

    public void addError(String key, String bundle, Object arguments[], int index)
    {
        errors.add(index, new Message(key, bundle, arguments));
    }

    /**
     * Adds a error message where the key is not of interest (internally this is
     * stored in the map with the given key as a key). This method is usally used
     * together with getInformationMessages()
     *
     * @param category representing the category e.g. form field of the message
     * @param key representing the key field in a message
     * @param bundle The i18n bundle name
     * @param arguments the parametrized part of a message
     */
    public void addError(String category, String key, String bundle, Object arguments[])
    {
        errors.add(new Message(category, key, bundle, arguments));
    }

    public void addError(String category, String key, String bundle, Object arguments[], int index)
    {
        errors.add(index, new Message(category, key, bundle, arguments));
    }


    /**
     * Merge another instance of MessageHelper into the current instance.
     * @param messageHelper
     */
    public MessageHolder merge(MessageHolder messageHelper)
    {
        this.getErrors().addAll(messageHelper.getErrors());
        this.getInformations().addAll(messageHelper.getInformations());
        return this;
    }

    public List<Message> getErrors()
    {
        if (errors == null)
        {
            errors = new ArrayList();
        }
        return errors;
    }

    public List<Message> getInformations()
    {
        if (informations == null)
        {
            informations = new ArrayList();
        }
        return informations;
    }

}

