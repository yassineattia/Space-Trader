package edu.gatech.cs2340.spacetrader.viewmodel

import android.util.Log
import android.view.View
import edu.gatech.cs2340.spacetrader.R
import edu.gatech.cs2340.spacetrader.entity.Skill
import edu.gatech.cs2340.spacetrader.entity.SkillsData
import edu.gatech.cs2340.spacetrader.util.DataType
import edu.gatech.cs2340.spacetrader.util.SkilledData
import edu.gatech.cs2340.spacetrader.validators.SkillValidator
import edu.gatech.cs2340.spacetrader.views.configfragments.SkillFragment
import kotlinx.android.synthetic.main.fragment_skill_selection.view.*

class SkillSelectionViewModel: SkilledData, ValidatableConfigViewModel {
    private val skills = SkillsData(mutableMapOf())
    private var usedSkillPoints = 0
    private val validator: SkillValidator = SkillValidator(this)

    fun handleViewCreation(view: View, fragment: SkillFragment) {
        val pointsLeftTextView = view.skill_selection_pointsLeft
        val pointsPilot = view.skill_pilot_value
        val pointsFighter = view.skill_fighter_value
        val pointsTrader = view.skill_trader_value
        val pointsEngineer = view.skill_engineer_value

        fun updateUnassignedPoints() {
            pointsLeftTextView.text =
                    fragment.getString(R.string.skill_selection_pointsLeft, getPointsLeft())
        }

        fun updatePilotPoints() {
            pointsPilot.text =
                    fragment.getString(R.string.skill_points_current, getSkillPoints(Skill.PILOT))
        }

        fun updateFighterPoints() {
            pointsFighter.text =
                    fragment.getString(R.string.skill_points_current, getSkillPoints(Skill.FIGHTER))
        }

        fun updateTraderPoints() {
            pointsTrader.text =
                    fragment.getString(R.string.skill_points_current, getSkillPoints(Skill.TRADER))
        }

        fun updateEngineerPoints() {
            pointsEngineer.text = fragment.getString(
                    R.string.skill_points_current, getSkillPoints(Skill.ENGINEER)
            )
        }

        updateUnassignedPoints()
        updatePilotPoints()
        updateFighterPoints()
        updateTraderPoints()
        updateEngineerPoints()

        view.skill_pilot_minusButton.setOnClickListener {
            removeSkillPoint(Skill.PILOT)
            updateUnassignedPoints()
            updatePilotPoints()
        }

        view.skill_pilot_plusButton.setOnClickListener {
            addSkillPoint(Skill.PILOT)
            updateUnassignedPoints()
            updatePilotPoints()
        }

        view.skill_fighter_minusButton.setOnClickListener {
            removeSkillPoint(Skill.FIGHTER)
            updateUnassignedPoints()
            updateFighterPoints()
        }

        view.skill_fighter_plusButton.setOnClickListener {
            addSkillPoint(Skill.FIGHTER)
            updateUnassignedPoints()
            updateFighterPoints()
        }

        view.skill_trader_minusButton.setOnClickListener {
            removeSkillPoint(Skill.TRADER)
            updateUnassignedPoints()
            updateTraderPoints()
        }

        view.skill_trader_plusButton.setOnClickListener {
            addSkillPoint(Skill.TRADER)
            updateUnassignedPoints()
            updateTraderPoints()
        }

        view.skill_engineer_minusButton.setOnClickListener {
            removeSkillPoint(Skill.ENGINEER)
            updateUnassignedPoints()
            updateEngineerPoints()
        }

        view.skill_engineer_plusButton.setOnClickListener {
            addSkillPoint(Skill.ENGINEER)
            updateUnassignedPoints()
            updateEngineerPoints()
        }
    }

    private fun addSkillPoint(skill : Skill) {
        //Log.d("SSVM", "Adding: ${usedSkillPoints} + 1 = ${usedSkillPoints + 1}")
        if (usedSkillPoints < MAX_SKILL_POINTS) {
            skills.addPoint(skill)
            usedSkillPoints++
        }
    }

    private fun removeSkillPoint(skill : Skill) {
        //Log.d("SSVM", "Removing: ${usedSkillPoints} - 1 = ${usedSkillPoints - 1}")
        if (usedSkillPoints > 0 && skills.skillsMap[skill]!! > 0) {
            skills.removePoint(skill)
            usedSkillPoints--
        }
    }

    private fun getPointsLeft() : Int {
        return MAX_SKILL_POINTS - usedSkillPoints
    }

    private fun getSkillPoints(skill : Skill) : Int {
        Log.d("SSVM", "Getting ${skills.skillsMap[skill]} points for $skill")
        return skills.skillsMap[skill]!!
    }

    override fun isFullyAllocated(): Boolean {
        return usedSkillPoints == MAX_SKILL_POINTS
    }

    override fun validate(): Boolean {
        return validator.validate()
    }

    override fun getInvalidMessage(): String {
        return "All 16 skill points must be allocated!"
    }

    override fun processData(): Any {
        return skills
    }

    override fun getDataType(): DataType {
        return DataType.SKILLS
    }

    companion object {
        const val MAX_SKILL_POINTS = 16
    }
}