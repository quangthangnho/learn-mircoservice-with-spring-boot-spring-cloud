package com.personal.microservice.limitservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/*
 * @created 20/09/2022 - 10:43
 * @author quang
 * @project limit-service
 */
@Getter
@Setter
@AllArgsConstructor()
public class Limits {

    private int min;
    private int max;
}
