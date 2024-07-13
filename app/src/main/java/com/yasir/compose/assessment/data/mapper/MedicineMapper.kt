package com.yasir.compose.assessment.data.mapper

import com.yasir.compose.assessment.data.remote.model.RemoteAssociatedDrug
import com.yasir.compose.assessment.data.remote.model.RemoteAssociatedDrug2
import com.yasir.compose.assessment.data.remote.model.RemoteClassName
import com.yasir.compose.assessment.data.remote.model.RemoteClassName2
import com.yasir.compose.assessment.data.remote.model.RemoteDiabete
import com.yasir.compose.assessment.data.remote.model.RemoteLab
import com.yasir.compose.assessment.data.remote.model.RemoteMedicationClasses
import com.yasir.compose.assessment.data.remote.model.RemoteMedications
import com.yasir.compose.assessment.data.remote.model.RemoteProblem
import com.yasir.compose.assessment.domain.model.AssociatedDrug
import com.yasir.compose.assessment.domain.model.AssociatedDrug2
import com.yasir.compose.assessment.domain.model.ClassName
import com.yasir.compose.assessment.domain.model.ClassName2
import com.yasir.compose.assessment.domain.model.Diabete
import com.yasir.compose.assessment.domain.model.Lab
import com.yasir.compose.assessment.domain.model.Medication
import com.yasir.compose.assessment.domain.model.MedicationsClasse
import com.yasir.compose.assessment.domain.model.Problem
import javax.inject.Inject

class MedicineMapper @Inject constructor() {

    fun mapToProblems(remoteMedicine: List<RemoteProblem>): List<Problem> {

        return remoteMedicine.map {
            Problem(
                diabetes = mapToDiabetes(it.diabetes),
                asthma = emptyList()
            )
        } ?: emptyList()
    }

    /*private fun mapToAsthma(asthma: List<RemoteAsthma>?): Asthma {
        return asthma.map {
            Asthma(
                it
            )
        } ?: emptyList()
    }*/

    private fun mapToDiabetes(diabetes: List<RemoteDiabete>?): List<Diabete> =
        diabetes?.map {
            Diabete(
                labs = mapToLabs(it.labs),
                medications = mapToMedication(it.medications)
            )
        } ?: emptyList()

    private fun mapToLabs(labs: List<RemoteLab>?): List<Lab> {
        return labs?.map {
            Lab(
                missingField = it.missingField.orEmpty()
            )
        } ?: emptyList()
    }

    private fun mapToMedication(medications: List<RemoteMedications>?) : List<Medication> =
        medications?.map {
            Medication(
                medicationsClasses = mapToMedicationClasse(it.medicationClasses)
            )
        } ?: emptyList()

    private fun mapToMedicationClasse(medications: List<RemoteMedicationClasses>?): List<MedicationsClasse> =
        medications?.map {
            MedicationsClasse(
                className = mapToClassName(it.className),
                className2 = mapToClassName2(it.className2)
            )
        } ?: emptyList()


    private fun mapToClassName(className: List<RemoteClassName>?): List<ClassName> =
        className?.map {
            ClassName(
                associatedDrug = mapToAssociatedDrug(it.associatedDrug),
                associatedDrug2 = mapToAssociatedDrug2(it.associatedDrug2)
            )
        } ?: emptyList()

    private fun mapToClassName2(className2: List<RemoteClassName2>?): List<ClassName2> =
        className2?.map {
            ClassName2(
                associatedDrug = mapToAssociatedDrug(it.associatedDrug),
                associatedDrug2 = mapToAssociatedDrug2(it.associatedDrug2)
            )
        } ?: emptyList()

    private fun mapToAssociatedDrug(associatedDrug: List<RemoteAssociatedDrug>?): List<AssociatedDrug> =
        associatedDrug?.map {
            AssociatedDrug(
                dose = it.dose.orEmpty(),
                name = it.name.orEmpty(),
                strength = it.strength.orEmpty()
            )
        } ?: emptyList()

    private fun mapToAssociatedDrug2(associatedDrug2: List<RemoteAssociatedDrug2>?): List<AssociatedDrug2> =
        associatedDrug2?.map {
            AssociatedDrug2(
                dose = it.dose.orEmpty(),
                name = it.name.orEmpty(),
                strength = it.strength.orEmpty()
            )
        } ?: emptyList()

}