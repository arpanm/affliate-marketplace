import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities as getCompetitions } from 'app/entities/competition/competition.reducer';
import { PrizeType } from 'app/shared/model/enumerations/prize-type.model';
import { PrizeValueType } from 'app/shared/model/enumerations/prize-value-type.model';
import { createEntity, getEntity, reset, updateEntity } from './prize.reducer';

export const PrizeUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const competitions = useAppSelector(state => state.competition.entities);
  const prizeEntity = useAppSelector(state => state.prize.entity);
  const loading = useAppSelector(state => state.prize.loading);
  const updating = useAppSelector(state => state.prize.updating);
  const updateSuccess = useAppSelector(state => state.prize.updateSuccess);
  const prizeTypeValues = Object.keys(PrizeType);
  const prizeValueTypeValues = Object.keys(PrizeValueType);

  const handleClose = () => {
    navigate('/prize');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getCompetitions({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    if (values.id !== undefined && typeof values.id !== 'number') {
      values.id = Number(values.id);
    }
    if (values.prizeValue !== undefined && typeof values.prizeValue !== 'number') {
      values.prizeValue = Number(values.prizeValue);
    }
    if (values.countOfPossibleWinners !== undefined && typeof values.countOfPossibleWinners !== 'number') {
      values.countOfPossibleWinners = Number(values.countOfPossibleWinners);
    }

    const entity = {
      ...prizeEntity,
      ...values,
      competition: competitions.find(it => it.id.toString() === values.competition?.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          prizeType: 'Individual',
          prizeValueType: 'Money',
          ...prizeEntity,
          competition: prizeEntity?.competition?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="affliateMarketplaceApp.prize.home.createOrEditLabel" data-cy="PrizeCreateUpdateHeading">
            <Translate contentKey="affliateMarketplaceApp.prize.home.createOrEditLabel">Create or edit a Prize</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="prize-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('affliateMarketplaceApp.prize.prizeType')}
                id="prize-prizeType"
                name="prizeType"
                data-cy="prizeType"
                type="select"
              >
                {prizeTypeValues.map(prizeType => (
                  <option value={prizeType} key={prizeType}>
                    {translate(`affliateMarketplaceApp.PrizeType.${prizeType}`)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('affliateMarketplaceApp.prize.prizeTag')}
                id="prize-prizeTag"
                name="prizeTag"
                data-cy="prizeTag"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.prize.prizeDetails')}
                id="prize-prizeDetails"
                name="prizeDetails"
                data-cy="prizeDetails"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.prize.prizeValueType')}
                id="prize-prizeValueType"
                name="prizeValueType"
                data-cy="prizeValueType"
                type="select"
              >
                {prizeValueTypeValues.map(prizeValueType => (
                  <option value={prizeValueType} key={prizeValueType}>
                    {translate(`affliateMarketplaceApp.PrizeValueType.${prizeValueType}`)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('affliateMarketplaceApp.prize.prizeValue')}
                id="prize-prizeValue"
                name="prizeValue"
                data-cy="prizeValue"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.prize.countOfPossibleWinners')}
                id="prize-countOfPossibleWinners"
                name="countOfPossibleWinners"
                data-cy="countOfPossibleWinners"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.prize.isActive')}
                id="prize-isActive"
                name="isActive"
                data-cy="isActive"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.prize.createdBy')}
                id="prize-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.prize.createdOn')}
                id="prize-createdOn"
                name="createdOn"
                data-cy="createdOn"
                type="date"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.prize.updatedBy')}
                id="prize-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.prize.updatedOn')}
                id="prize-updatedOn"
                name="updatedOn"
                data-cy="updatedOn"
                type="date"
              />
              <ValidatedField
                id="prize-competition"
                name="competition"
                data-cy="competition"
                label={translate('affliateMarketplaceApp.prize.competition')}
                type="select"
              >
                <option value="" key="0" />
                {competitions
                  ? competitions.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/prize" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default PrizeUpdate;
