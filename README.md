## Exercício TDD DevSuperior

<h4 align="left">
 Solucionar TDD apresentado com testes integrados na camada Web
</h4>

<!--ts-->

* [Autor](#autor)
* [Tecnologias](#tecnologias)
* [Desafio](#desafio)

---

### Autor

<a href="https://www.linkedin.com/in/marcelomachado1987/">
 <img style="border-radius: 50%;" src="https://media-exp1.licdn.com/dms/image/C4E03AQEwV54JjLc-9g/profile-displayphoto-shrink_800_800/0/1621682542460?e=1626912000&v=beta&t=Ctis1Z8wFBsNtnuMhTXGp7cXWA12JyY5t9KF9rfQf58" width="100px;" alt=""/>
 <br />
<b>Marcelo Machado</b></a>
 <br />

---

### 🛠 Tecnologias

As seguintes ferramentas foram usadas na construção do projeto:

- [Spring Boot](https://spring.io/projects)
- [Java 11](https://docs.oracle.com/en/java/javase/11/)
- [Hibernate + JPA](https://hibernate.org/)
- [SQL]()
- [JUnit5](https://junit.org/junit5/)
- [Mockito](https://site.mockito.org/)

---

### 🎲 O desafio consiste em Construir API  do modelo abaixo para passar os testes a seguir

<img src= "img/modelo.png" />

```Java
@Test
public void deleteShouldReturnBadRequestWhenDependentId()throws Exception{

    Long dependentId=1L;

    ResultActions result=
    mockMvc.perform(delete("/cities/{id}",dependentId));

    result.andExpect(status().isBadRequest());
    }

@Test
public void deleteShouldReturnNoContentWhenIndependentId()throws Exception{

    Long independentId=5L;

    ResultActions result=
    mockMvc.perform(delete("/cities/{id}",independentId));


    result.andExpect(status().isNoContent());
    }

@Test
public void deleteShouldReturnNotFoundWhenNonExistingId()throws Exception{

    Long nonExistingId=50L;

    ResultActions result=
    mockMvc.perform(delete("/cities/{id}",nonExistingId));

    result.andExpect(status().isNotFound());
    }

@Test
public void findAllShouldReturnAllResourcesSortedByName()throws Exception{

    ResultActions result=
    mockMvc.perform(get("/cities")
    .contentType(MediaType.APPLICATION_JSON));

    result.andExpect(status().isOk());
    result.andExpect(jsonPath("$[0].name").value("Belo Horizonte"));
    result.andExpect(jsonPath("$[1].name").value("Belém"));
    result.andExpect(jsonPath("$[2].name").value("Brasília"));
    }

@Test
public void updateShouldUpdateResourceWhenIdExists()throws Exception{

    long existingId=1L;

    EventDTO dto=new EventDTO(null,"Expo XP",LocalDate.of(2021,5,18),"https://expoxp.com.br",7L);
    String jsonBody=objectMapper.writeValueAsString(dto);

    ResultActions result=
    mockMvc.perform(put("/events/{id}",existingId)
    .content(jsonBody)
    .contentType(MediaType.APPLICATION_JSON)
    .accept(MediaType.APPLICATION_JSON));

    result.andExpect(status().isOk());
    result.andExpect(jsonPath("$.id").exists());
    result.andExpect(jsonPath("$.id").value(1L));
    result.andExpect(jsonPath("$.name").value("Expo XP"));
    result.andExpect(jsonPath("$.date").value("2021-05-18"));
    result.andExpect(jsonPath("$.url").value("https://expoxp.com.br"));
    result.andExpect(jsonPath("$.cityId").value(7L));
    }

@Test
public void updateShouldReturnNotFoundWhenIdDoesNotExist()throws Exception{

    long nonExistingId=1000L;

    EventDTO dto=new EventDTO(null,"Expo XP",LocalDate.of(2021,5,18),"https://expoxp.com.br",7L);
    String jsonBody=objectMapper.writeValueAsString(dto);

    ResultActions result=
    mockMvc.perform(put("/events/{id}",nonExistingId)
    .content(jsonBody)
    .contentType(MediaType.APPLICATION_JSON)
    .accept(MediaType.APPLICATION_JSON));

    result.andExpect(status().isNotFound());
    }



```