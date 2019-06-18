package com.stefanini.internship.authorizationserver.services;

import com.stefanini.internship.authorizationserver.dao.*;
import com.stefanini.internship.authorizationserver.dao.repositories.*;
import com.stefanini.internship.authorizationserver.exceptions.ResourceNotFoundException;
import com.stefanini.internship.authorizationserver.utils.AuthorizationResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AuthorizationServiceTest {

    @Mock
    OwaSidRepository sidRepository;

    @Mock
    OwaClassRepository classRepository;

    @Mock
    OwaObjectRepository objectRepository;

    @Mock
    OwaGrantRepository grantRepository;

    @Mock
    OwaClassGrantRepository classGrantRepository;

    @Mock
    OwaRoleRepository roleRepository;

    @Mock
    EntityValidationService validationService;


    @InjectMocks
    AuthorizationService authorizationService;



    @Test
    public void checkObject_RoleNull_GrantPresent() {
        /*Input data*/
        String classname = "PlaceRequest";
        String principal = "user";
        Long identifier = 40L;
        String permission = "read";

        /*mocked db results and queries*/
        OwaSid owaUserSid = new OwaSid(null, principal, null);
        OwaClass owaPRClass = new OwaClass(classname.toUpperCase());
        OwaObject owaPRObject = new OwaObject(owaPRClass, identifier);

        when(sidRepository.findBySid(principal)).thenReturn(owaUserSid);
        when(objectRepository.findByOwaClassClassnameAndIdentifier(classname, identifier)).thenReturn(owaPRObject);
        when(grantRepository.existsByObjectAndSidAndPermission(owaPRObject, owaUserSid, 1)).thenReturn(true);
        doNothing().when(validationService).AssertValidResult((OwaSid) notNull(), any());
        doNothing().when(validationService).AssertValidResult(notNull(), any(), any());

        /*call Service*/
        AuthorizationResponse response = authorizationService.checkObjectAuthorization(classname, identifier, principal, permission);

        assertTrue(response.isAuthorized());

    }

    @Test
    public void checkObject_RoleNull_GrantAbsent() {
        /*Input data*/
        String classname = "Place";
        String principal = "user";
        Long identifier = 10L;
        String permission = "read";

        /*mocked db results and queries*/
        OwaSid owaUserSid = new OwaSid(null, principal, null);
        OwaClass owaPRClass = new OwaClass(classname.toUpperCase());
        OwaObject owaPRObject = new OwaObject(owaPRClass, identifier);

        when(sidRepository.findBySid(principal)).thenReturn(owaUserSid);
        when(objectRepository.findByOwaClassClassnameAndIdentifier(classname, identifier)).thenReturn(owaPRObject);
        when(grantRepository.existsByObjectAndSidAndPermission(owaPRObject, owaUserSid, 1)).thenReturn(false);
        doNothing().when(validationService).AssertValidResult((OwaSid) notNull(), any());
        doNothing().when(validationService).AssertValidResult(notNull(), any(), any());

        /*call Service*/
        AuthorizationResponse response = authorizationService.checkObjectAuthorization(classname, identifier, principal, permission);
        assertFalse(response.isAuthorized());
    }

    @Test
    public void checkObject_RoleNotGranting_GrantAbsent() {
        /*Input data*/
        String classname = "Place";
        String principal = "user";
        Long identifier = 10L;
        String permission = "read";

        /*mocked db results and queries*/
        OwaRole role = new OwaRole("employee");
        OwaSid owaUserSid = new OwaSid(null, principal, role);
        OwaClass owaClass = new OwaClass(classname.toUpperCase());
        OwaObject owaObject = new OwaObject(owaClass, identifier);
        
        when(sidRepository.findBySid(principal)).thenReturn(owaUserSid);
        when(classRepository.findByClassname("PLACE")).thenReturn(owaClass);
        when(classGrantRepository.existsByOwaClassAndRoleAndPermission(owaClass, role, 1)).thenReturn(true);
        doNothing().when(validationService).AssertValidResult((OwaSid) notNull(), any());

        /*call Service*/
        AuthorizationResponse response = authorizationService.checkObjectAuthorization(classname, identifier, principal, permission);
        assertTrue(response.isAuthorized());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void checkObject_UserNotPresent() {
        /*Input data*/
        String classname = "PlaceRequest";
        String principal = "inexistingUser45636";
        Long identifier = 40L;
        String permission = "read";

        /*mocked db results and queries*/
        OwaSid owaUserSid = new OwaSid(null, principal, null);
        OwaClass owaPRClass = new OwaClass(classname.toUpperCase());
        OwaObject owaPRObject = new OwaObject(owaPRClass, identifier);

        when(sidRepository.findBySid(principal)).thenReturn(null);

        doThrow(new ResourceNotFoundException("")).when(validationService).AssertValidResult((OwaSid)isNull(),any());

        /*call Service*/
        AuthorizationResponse response = authorizationService.checkObjectAuthorization(classname, identifier, principal, permission);

        fail("Should have thrown error");
    }

    @Test(expected = ResourceNotFoundException.class)
    public void checkObject_wrongClass() {
        /*Input data*/
        String classname = "Class Not Exists";
        String principal = "user";
        Long identifier = 40L;
        String permission = "read";

        /*mocked db results and queries*/
        OwaSid owaUserSid = new OwaSid(null, principal, null);
        OwaClass owaPRClass = new OwaClass(classname.toUpperCase());
        OwaObject owaPRObject = new OwaObject(owaPRClass, identifier);

        when(sidRepository.findBySid(principal)).thenReturn(owaUserSid);
        when(objectRepository.findByOwaClassClassnameAndIdentifier(classname, identifier)).thenReturn(null);
        doThrow(new ResourceNotFoundException("")).when(validationService).AssertValidResult(isNull(),any(), any());
        doNothing().when(validationService).AssertValidResult((OwaSid) notNull(), any());


        /*call Service*/
        AuthorizationResponse response = authorizationService.checkObjectAuthorization(classname, identifier, principal, permission);

        fail("Should have thrown error");

    }

    @Test(expected = ResourceNotFoundException.class)
    public void checkObject_wrongIdentifier() {
        /*Input data*/
        String classname = "Class Not Exists";
        String principal = "user";
        Long identifier = 9633L;
        String permission = "read";

        /*mocked db results and queries*/
        OwaSid owaUserSid = new OwaSid(null, principal, null);
        OwaClass owaPRClass = new OwaClass(classname.toUpperCase());
        OwaObject owaPRObject = new OwaObject(owaPRClass, identifier);

        when(sidRepository.findBySid(principal)).thenReturn(owaUserSid);
        when(objectRepository.findByOwaClassClassnameAndIdentifier(classname, identifier)).thenReturn(null);
        doThrow(new ResourceNotFoundException("")).when(validationService).AssertValidResult(isNull(),any(), any());
        doNothing().when(validationService).AssertValidResult((OwaSid) notNull(), any());


        /*call Service*/
        AuthorizationResponse response = authorizationService.checkObjectAuthorization(classname, identifier, principal, permission);

        fail("Should have thrown error");

    }

    @Test
    public void checkClassAuthorization1() {
    }

    @Test
    public void checkObjectAuthorization() {
    }

    @Test
    public void checkObjectAuthorization1() {
    }
}