package com.tfg.services;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.vocabulary.VCARD;
import org.apache.jena.vocabulary.VCARD4;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import org.apache.jena.rdf.model.Property;

@Component
public class PropertiesFactory {

    private static final HashMap<String, Property> properties = new HashMap<String, Property>();
    static{
        properties.put("additional_name".toLowerCase(), VCARD4.additional_name);
        properties.put("adr".toLowerCase(), VCARD4.adr);
        properties.put("agent".toLowerCase(), VCARD4.agent);
        properties.put("anniversary".toLowerCase(), VCARD4.anniversary);
        properties.put("bday".toLowerCase(), VCARD4.bday);
        properties.put("category".toLowerCase(), VCARD4.category);
        properties.put("class_prop".toLowerCase(), VCARD4.class_prop);
        properties.put("country_name".toLowerCase(), VCARD4.country_name);
        properties.put("email".toLowerCase(), VCARD4.email);
        properties.put("extended_address".toLowerCase(), VCARD4.extended_address);
        properties.put("family_name".toLowerCase(), VCARD4.family_name);
        properties.put("fn".toLowerCase(), VCARD4.fn);
        properties.put("geo".toLowerCase(), VCARD4.geo);
        properties.put("given_name".toLowerCase(), VCARD4.given_name);
        properties.put("hasAdditionalName".toLowerCase(), VCARD4.hasAdditionalName);
        properties.put("hasAddress".toLowerCase(), VCARD4.hasAddress);
        properties.put("hasCalendarBusy".toLowerCase(), VCARD4.hasCalendarBusy);
        properties.put("hasCalendarLink".toLowerCase(), VCARD4.hasCalendarLink);
        properties.put("hasCalendarRequest".toLowerCase(), VCARD4.hasCalendarRequest);
        properties.put("hasCategory".toLowerCase(), VCARD4.hasCategory);
        properties.put("hasCountryName".toLowerCase(), VCARD4.hasCountryName);
        properties.put("hasEmail".toLowerCase(), VCARD4.hasEmail);
        properties.put("hasFN".toLowerCase(), VCARD4.hasFN);
        properties.put("hasFamilyName".toLowerCase(), VCARD4.hasFamilyName);
        properties.put("hasGender".toLowerCase(), VCARD4.hasGender);
        properties.put("hasGeo".toLowerCase(), VCARD4.hasGeo);
        properties.put("hasGivenName".toLowerCase(), VCARD4.hasGivenName);
        properties.put("hasHonorificPrefix".toLowerCase(), VCARD4.hasHonorificPrefix);
        properties.put("hasHonorificSuffix".toLowerCase(), VCARD4.hasHonorificSuffix);
        properties.put("hasInstantMessage".toLowerCase(), VCARD4.hasInstantMessage);
        properties.put("hasKey".toLowerCase(), VCARD4.hasKey);
        properties.put("hasLanguage".toLowerCase(), VCARD4.hasLanguage);
        properties.put("hasLocality".toLowerCase(), VCARD4.hasLocality);
        properties.put("hasLogo".toLowerCase(), VCARD4.hasLogo);
        properties.put("hasMember".toLowerCase(), VCARD4.hasMember);
        properties.put("hasName".toLowerCase(), VCARD4.hasName);
        properties.put("hasNickname".toLowerCase(), VCARD4.hasNickname);
        properties.put("hasNote".toLowerCase(), VCARD4.hasNote);
        properties.put("hasOrganizationName".toLowerCase(), VCARD4.hasOrganizationName);
        properties.put("hasOrganizationUnit".toLowerCase(), VCARD4.hasOrganizationUnit);
        properties.put("hasPhoto".toLowerCase(), VCARD4.hasPhoto);
        properties.put("hasPostalCode".toLowerCase(), VCARD4.hasPostalCode);
        properties.put("hasRegion".toLowerCase(), VCARD4.hasRegion);
        properties.put("hasRelated".toLowerCase(), VCARD4.hasRelated);
        properties.put("hasRole".toLowerCase(), VCARD4.hasRole);
        properties.put("hasSound".toLowerCase(), VCARD4.hasSound);
        properties.put("hasSource".toLowerCase(), VCARD4.hasSource);
        properties.put("hasStreetAddress".toLowerCase(), VCARD4.hasStreetAddress);
        properties.put("hasTelephone".toLowerCase(), VCARD4.hasTelephone);
        properties.put("hasTitle".toLowerCase(), VCARD4.hasTitle);
        properties.put("hasUID".toLowerCase(), VCARD4.hasUID);
        properties.put("hasURL".toLowerCase(), VCARD4.hasURL);
        properties.put("hasValue".toLowerCase(), VCARD4.hasValue);
        properties.put("honorific_prefix".toLowerCase(), VCARD4.honorific_prefix);
        properties.put("honorific_suffix".toLowerCase(), VCARD4.honorific_suffix);
        properties.put("key".toLowerCase(), VCARD4.key);
        properties.put("label".toLowerCase(), VCARD4.label);
        properties.put("language".toLowerCase(), VCARD4.language);
        properties.put("latitude".toLowerCase(), VCARD4.latitude);
        properties.put("locality".toLowerCase(), VCARD4.locality);
        properties.put("logo".toLowerCase(), VCARD4.logo);
        properties.put("longitude".toLowerCase(), VCARD4.longitude);
        properties.put("mailer".toLowerCase(), VCARD4.mailer);
        properties.put("n".toLowerCase(), VCARD4.n);
        properties.put("nickname".toLowerCase(), VCARD4.nickname);
        properties.put("note".toLowerCase(), VCARD4.note);
        properties.put("org".toLowerCase(), VCARD4.org);
        properties.put("organization_name".toLowerCase(), VCARD4.organization_name);
        properties.put("organization_unit".toLowerCase(), VCARD4.organization_unit);
        properties.put("photo".toLowerCase(), VCARD4.photo);
        properties.put("post_office_box".toLowerCase(), VCARD4.post_office_box);
        properties.put("postal_code".toLowerCase(), VCARD4.postal_code);
        properties.put("prodid".toLowerCase(), VCARD4.prodid);
        properties.put("region".toLowerCase(), VCARD4.region);
        properties.put("rev".toLowerCase(), VCARD4.rev);
        properties.put("role".toLowerCase(), VCARD4.role);
        properties.put("sort_string".toLowerCase(), VCARD4.sort_string);
        properties.put("sound".toLowerCase(), VCARD4.sound);
        properties.put("street_address".toLowerCase(), VCARD4.street_address);
        properties.put("tel".toLowerCase(), VCARD4.tel);
        properties.put("title".toLowerCase(), VCARD4.title);
        properties.put("tz".toLowerCase(), VCARD4.tz);
        properties.put("url".toLowerCase(), VCARD4.url);
        properties.put("value".toLowerCase(), VCARD4.value);
        properties.put("Street".toLowerCase(), VCARD.Street);
        properties.put("AGENT".toLowerCase(), VCARD.AGENT);
        properties.put("SOURCE".toLowerCase(), VCARD.SOURCE);
        properties.put("LOGO".toLowerCase(), VCARD.LOGO);
        properties.put("BDAY".toLowerCase(), VCARD.BDAY);
        properties.put("REV".toLowerCase(), VCARD.REV);
        properties.put("SORT_STRING".toLowerCase(), VCARD.SORT_STRING);
        properties.put("Orgname".toLowerCase(), VCARD.Orgname);
        properties.put("CATEGORIES".toLowerCase(), VCARD.CATEGORIES);
        properties.put("N".toLowerCase(), VCARD.N);
        properties.put("Pcode".toLowerCase(), VCARD.Pcode);
        properties.put("Prefix".toLowerCase(), VCARD.Prefix);
        properties.put("PHOTO".toLowerCase(), VCARD.PHOTO);
        properties.put("FN".toLowerCase(), VCARD.FN);
        properties.put("ORG".toLowerCase(), VCARD.ORG);
        properties.put("Suffix".toLowerCase(), VCARD.Suffix);
        properties.put("CLASS".toLowerCase(), VCARD.CLASS);
        properties.put("ADR".toLowerCase(), VCARD.ADR);
        properties.put("Region".toLowerCase(), VCARD.Region);
        properties.put("GEO".toLowerCase(), VCARD.GEO);
        properties.put("Extadd".toLowerCase(), VCARD.Extadd);
        properties.put("GROUP".toLowerCase(), VCARD.GROUP);
        properties.put("EMAIL".toLowerCase(), VCARD.EMAIL);
        properties.put("UID".toLowerCase(), VCARD.UID);
        properties.put("Family".toLowerCase(), VCARD.Family);
        properties.put("TZ".toLowerCase(), VCARD.TZ);
        properties.put("NAME".toLowerCase(), VCARD.NAME);
        properties.put("Orgunit".toLowerCase(), VCARD.Orgunit);
        properties.put("Country".toLowerCase(), VCARD.Country);
        properties.put("SOUND".toLowerCase(), VCARD.SOUND);
        properties.put("TITLE".toLowerCase(), VCARD.TITLE);
        properties.put("NOTE".toLowerCase(), VCARD.NOTE);
        properties.put("MAILER".toLowerCase(), VCARD.MAILER);
        properties.put("Other".toLowerCase(), VCARD.Other);
        properties.put("Locality".toLowerCase(), VCARD.Locality);
        properties.put("Pobox".toLowerCase(), VCARD.Pobox);
        properties.put("KEY".toLowerCase(), VCARD.KEY);
        properties.put("PRODID".toLowerCase(), VCARD.PRODID);
        properties.put("Given".toLowerCase(), VCARD.Given);
        properties.put("LABEL".toLowerCase(), VCARD.LABEL);
        properties.put("TEL".toLowerCase(), VCARD.TEL);
        properties.put("NICKNAME".toLowerCase(), VCARD.NICKNAME);
        properties.put("ROLE".toLowerCase(), VCARD.ROLE);
    }

    public static Property getProperty(String header) {
        Property property = properties.get(header);
        if(property != null) {
            return property;
        }
        property = getCustomProperty(header);
        return property;
    }

    private static Property getCustomProperty(String header){
        // TODO
        Model m = ModelFactory.createDefaultModel();
        return m.createProperty("", "");
    }
}
