package com.slokam.criteria.file;

import java.util.List;

import com.slokam.criteria.exception.FileGeneratorException;
import com.slokam.criteria.pojo.PersonPojo;

public interface IFileGenerator {

	public void getFileGenerator(String outputPath,List<PersonPojo> personList) throws FileGeneratorException;
}
