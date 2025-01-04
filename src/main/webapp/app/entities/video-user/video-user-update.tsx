import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, isNumber, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities as getBankDetails } from 'app/entities/bank-details/bank-details.reducer';
import { VideoUserType } from 'app/shared/model/enumerations/video-user-type.model';
import { createEntity, getEntity, reset, updateEntity } from './video-user.reducer';

export const VideoUserUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const bankDetails = useAppSelector(state => state.bankDetails.entities);
  const videoUserEntity = useAppSelector(state => state.videoUser.entity);
  const loading = useAppSelector(state => state.videoUser.loading);
  const updating = useAppSelector(state => state.videoUser.updating);
  const updateSuccess = useAppSelector(state => state.videoUser.updateSuccess);
  const videoUserTypeValues = Object.keys(VideoUserType);

  const handleClose = () => {
    navigate('/video-user');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getBankDetails({}));
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
    if (values.phone !== undefined && typeof values.phone !== 'number') {
      values.phone = Number(values.phone);
    }

    const entity = {
      ...videoUserEntity,
      ...values,
      bank: bankDetails.find(it => it.id.toString() === values.bank?.toString()),
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
          userType: 'SuperAdmin',
          ...videoUserEntity,
          bank: videoUserEntity?.bank?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="affliateMarketplaceApp.videoUser.home.createOrEditLabel" data-cy="VideoUserCreateUpdateHeading">
            <Translate contentKey="affliateMarketplaceApp.videoUser.home.createOrEditLabel">Create or edit a VideoUser</Translate>
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
                  id="video-user-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoUser.userId')}
                id="video-user-userId"
                name="userId"
                data-cy="userId"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoUser.userName')}
                id="video-user-userName"
                name="userName"
                data-cy="userName"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoUser.name')}
                id="video-user-name"
                name="name"
                data-cy="name"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoUser.phone')}
                id="video-user-phone"
                name="phone"
                data-cy="phone"
                type="text"
                validate={{
                  min: { value: 1000000000, message: translate('entity.validation.min', { min: 1000000000 }) },
                  max: { value: 9999999999, message: translate('entity.validation.max', { max: 9999999999 }) },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoUser.email')}
                id="video-user-email"
                name="email"
                data-cy="email"
                type="text"
                validate={{
                  pattern: { value: /^(.+)@(.+)$/, message: translate('entity.validation.pattern', { pattern: '^(.+)@(.+)$' }) },
                }}
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoUser.description')}
                id="video-user-description"
                name="description"
                data-cy="description"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoUser.imageUrl')}
                id="video-user-imageUrl"
                name="imageUrl"
                data-cy="imageUrl"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoUser.userType')}
                id="video-user-userType"
                name="userType"
                data-cy="userType"
                type="select"
              >
                {videoUserTypeValues.map(videoUserType => (
                  <option value={videoUserType} key={videoUserType}>
                    {translate(`affliateMarketplaceApp.VideoUserType.${videoUserType}`)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoUser.isBlocked')}
                id="video-user-isBlocked"
                name="isBlocked"
                data-cy="isBlocked"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoUser.blockedTill')}
                id="video-user-blockedTill"
                name="blockedTill"
                data-cy="blockedTill"
                type="date"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoUser.isActive')}
                id="video-user-isActive"
                name="isActive"
                data-cy="isActive"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoUser.createdBy')}
                id="video-user-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoUser.createdOn')}
                id="video-user-createdOn"
                name="createdOn"
                data-cy="createdOn"
                type="date"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoUser.updatedBy')}
                id="video-user-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoUser.updatedOn')}
                id="video-user-updatedOn"
                name="updatedOn"
                data-cy="updatedOn"
                type="date"
              />
              <ValidatedField
                id="video-user-bank"
                name="bank"
                data-cy="bank"
                label={translate('affliateMarketplaceApp.videoUser.bank')}
                type="select"
              >
                <option value="" key="0" />
                {bankDetails
                  ? bankDetails.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/video-user" replace color="info">
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

export default VideoUserUpdate;
