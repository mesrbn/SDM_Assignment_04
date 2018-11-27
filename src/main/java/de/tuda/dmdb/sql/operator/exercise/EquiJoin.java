package de.tuda.dmdb.sql.operator.exercise;

import de.tuda.dmdb.sql.operator.EquiJoinBase;
import de.tuda.dmdb.sql.operator.Operator;
import de.tuda.dmdb.storage.AbstractRecord;

import java.util.ArrayList;

public class EquiJoin extends EquiJoinBase {
	
	public EquiJoin(Operator leftChild, Operator rightChild, int leftAtt, int rightAtt) {
		super(leftChild, rightChild, leftAtt, rightAtt);
	}
	
	@Override
	public void open() {
		//TODO: implement this method
		leftChild.open();
		rightChild.open();
	}

	@Override
	public AbstractRecord next() {
		//TODO: implement this method
		AbstractRecord leftOpRec = this.leftRecord;
		if (leftOpRec == null) {
			leftOpRec = leftChild.next();
		}
		AbstractRecord rightOpRec = rightChild.next();
		while (rightOpRec != null) {
			if (leftOpRec.getValue(leftAtt).equals(rightOpRec.getValue(rightAtt))) {
				leftRecord = leftOpRec;
				AbstractRecord result = leftOpRec.append(rightOpRec);
				return result;
			} else
				rightOpRec = rightChild.next();
		}
		rightChild.open();
		this.leftRecord = leftChild.next();

		if (leftRecord != null)
			return next();

		return null;
	}

	@Override
	public void close() {
		//TODO: implement this method
		leftChild.close();
		rightChild.close();
	}
}
