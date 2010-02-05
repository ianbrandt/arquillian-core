/*
 * JBoss, Home of Professional Open Source
 * Copyright 2009, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.acme.jpa;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * UserRepositoryBean
 *
 * @author <a href="mailto:aslak@conduct.no">Aslak Knutsen</a>
 * @version $Revision: $
 */
@Local(UserRepository.class)
@Stateless
public class UserRepositoryBean implements UserRepository
{

   @PersistenceContext(unitName = "Domain")
   private EntityManager entityManager;
   
   /* (non-Javadoc)
    * @see com.acme.cdi.jpa.UserRepository#getByFirstName(java.lang.String)
    */
   @SuppressWarnings("unchecked")
   @Override
   public List<User> getByFirstName(String firstName)
   {
      return entityManager.createQuery("from User user where user.firstName = :firstName")
                           .setParameter("firstName", firstName)
                           .getResultList();
   }

   /* (non-Javadoc)
    * @see com.acme.cdi.jpa.UserRepository#store(com.acme.cdi.jpa.User)
    */
   @Override
   public void store(User user)
   {
      entityManager.persist(user);
   }

}